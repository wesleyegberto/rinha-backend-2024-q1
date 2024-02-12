package com.github.wesleyegberto.apitransacoes.transacoes;

import com.github.wesleyegberto.apitransacoes.clientes.Cliente;

public class TransacionaResponse {
	private long limite;
	private long saldo;

	public TransacionaResponse(Cliente cliente) {
		this.limite = cliente.getLimite();
		this.saldo = cliente.getSaldo();
	}

	public long getLimite() {
		return limite;
	}

	public long getSaldo() {
		return saldo;
	}
}
