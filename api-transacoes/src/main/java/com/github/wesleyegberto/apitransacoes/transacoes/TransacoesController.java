package com.github.wesleyegberto.apitransacoes.transacoes;

import java.util.Map;
import java.util.Optional;

import com.github.wesleyegberto.apitransacoes.clientes.Cliente;
import com.github.wesleyegberto.apitransacoes.clientes.ClientesRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/clientes/{idCliente}")
public class TransacoesController {
	private final EntityManager em;
	private final ClientesRepository clientes;
	private final TransacoesRepository transacoes;

	public TransacoesController(EntityManager em, ClientesRepository clientes, TransacoesRepository transacoes) {
		this.em = em;
		this.clientes = clientes;
		this.transacoes = transacoes;
	}

	@Transactional
	@PostMapping("transacoes")
	public ResponseEntity<TransacionaResponse> transaciona(@PathVariable("idCliente") int idCliente,
			@RequestBody Transacao transacao) {
		if (transacao == null || !transacao.estaValida()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		var clienteOpt = clientes.findById(idCliente);
		if (clienteOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var cliente = clienteOpt.get();
		transacao.setIdCliente(idCliente);

		switch (transacao.getTipo()) {
			case Transacao.TIPO_CREDITO:
				clientes.credita(idCliente, transacao.getValor());
				break;

			case Transacao.TIPO_DEBITO:
				if (!cliente.consegueDebitar(transacao.getValor())) {
					return ResponseEntity.unprocessableEntity().build();
				}
				// this.em.lock(cliente, LockModeType.PESSIMISTIC_WRITE);
				clientes.debita(idCliente, transacao.getValor());
				break;
		}
		transacoes.save(transacao);

		return ResponseEntity.ok(new TransacionaResponse(cliente, transacao));
	}

	@GetMapping("extrato")
	public ResponseEntity<ExtratoResponse> getById(@PathVariable("idCliente") int idCliente) {
		Optional<Cliente> cliente = clientes.findById(idCliente);
		if (cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var ultimasTransacoes = transacoes.buscaUltimasTransacoes(idCliente);
		return ResponseEntity.ok(new ExtratoResponse(cliente.get(), ultimasTransacoes));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> trataErro(HttpMessageNotReadableException exception) {
		return ResponseEntity.unprocessableEntity().body(Map.of("erro", "Requisição inválida."));
	}
}
