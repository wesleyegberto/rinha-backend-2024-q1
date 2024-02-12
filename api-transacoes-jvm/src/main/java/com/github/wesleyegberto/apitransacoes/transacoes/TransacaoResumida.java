package com.github.wesleyegberto.apitransacoes.transacoes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;

public class TransacaoResumida {
	@Column(name = "realizado_em")
	@JsonProperty("realizado_em")
	private LocalDateTime realizadoEm;
	private char tipo;
	private long valor;
	private String descricao;

	public TransacaoResumida() {
	}

	public TransacaoResumida(LocalDateTime realizadoEm, char tipo, long valor, String descricao) {
		this.realizadoEm = realizadoEm;
		this.tipo = tipo;
		this.valor = valor;
		this.descricao = descricao;
	}

	public LocalDateTime getRealizadoEm() {
		return realizadoEm;
	}

	public char getTipo() {
		return tipo;
	}

	public long getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
}
