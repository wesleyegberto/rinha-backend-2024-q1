package com.github.wesleyegberto.apitransacoes.transacoes;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.wesleyegberto.apitransacoes.clientes.Cliente;

public class ExtratoResponse {
	private SaldoResponse saldo;
	@JsonProperty("ultimas_transacoes")
	private List<TransacaoResumida> ultimasTransacoes;

	public ExtratoResponse(Cliente cliente, Iterable<TransacaoResumida> transacoes) {
		this.saldo = new SaldoResponse(cliente);
		this.ultimasTransacoes = new ArrayList<>(10);
		if (transacoes != null) {
			transacoes.forEach(ultimasTransacoes::add);
		}
	}

	public SaldoResponse getSaldo() {
		return saldo;
	}

	public List<TransacaoResumida> getUltimasTransacoes() {
		return ultimasTransacoes;
	}
}
