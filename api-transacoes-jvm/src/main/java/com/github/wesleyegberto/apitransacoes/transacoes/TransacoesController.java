package com.github.wesleyegberto.apitransacoes.transacoes;

import java.util.Map;
import java.util.Optional;

import com.github.wesleyegberto.apitransacoes.clientes.Cliente;
import com.github.wesleyegberto.apitransacoes.clientes.ClientesRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
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
	public ResponseEntity<?> transaciona(@PathVariable("idCliente") int idCliente,
			@RequestBody Transacao transacao) {
		if (transacao == null || !transacao.estaValida()) {
			return ResponseEntity.badRequest().build();
		}

		var clienteOpt = clientes.findById(idCliente);
		if (clienteOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var cliente = clienteOpt.get();
		this.em.detach(cliente);
		transacao.setIdCliente(idCliente);

		switch (transacao.getTipo()) {
			case Transacao.TIPO_CREDITO:
				clientes.credita(idCliente, transacao.getValor());
				cliente.credita(transacao.getValor());
				break;
			case Transacao.TIPO_DEBITO:
				if (!cliente.consegueDebitar(transacao.getValor())) {
					return ResponseEntity.unprocessableEntity().body(Map.of("erro", "Saldo insuficiente para transação."));
				}
				clientes.debita(idCliente, transacao.getValor());
				cliente.debita(transacao.getValor());
				break;
			default:
				return ResponseEntity.badRequest().body(Map.of("erro", "Tipo de transação inváida."));
		}
		transacoes.save(transacao);

		return ResponseEntity.ok(new TransacionaResponse(cliente));
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
}
