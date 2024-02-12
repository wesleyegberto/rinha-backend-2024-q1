package com.github.wesleyegberto.apitransacoes.transacoes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.wesleyegberto.apitransacoes.clientes.Cliente;

public class SaldoResponse {
	private long total;
	private long limite;
	@JsonProperty("data_extrato")
	private LocalDateTime dataExtrato;

	public SaldoResponse(Cliente cliente) {
		this.total = cliente.getSaldo();
		this.limite = cliente.getLimite();
		this.dataExtrato = LocalDateTime.now();
	}

	public long getTotal() {
		return total;
	}

	public long getLimite() {
		return limite;
	}

	public LocalDateTime getDataExtrato() {
		return dataExtrato;
	}
}
