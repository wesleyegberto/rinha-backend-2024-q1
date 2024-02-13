package com.github.wesleyegberto.apitransacoes.transacoes;

import com.github.wesleyegberto.apitransacoes.clientes.Cliente;

public class TransacionaResponse {
	private long limite;
	private long saldo;

	public TransacionaResponse(Cliente cliente, Transacao transacao) {
		this.limite = cliente.getLimite();
		if (transacao.getTipo() == Transacao.TIPO_CREDITO) {
			this.saldo = cliente.getSaldo() + transacao.getValor();
		} else {
			this.saldo = cliente.getSaldo() - transacao.getValor();
		}
	}

	public long getLimite() {
		return limite;
	}

	public long getSaldo() {
		return saldo;
	}
}
