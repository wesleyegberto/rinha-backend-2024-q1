package com.github.wesleyegberto.apitransacoes.transacoes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes", indexes = {
		@Index(name = "ultimas_transacoes_idx", columnList = "id_cliente ASC, realizada_em DESC")
})
public class Transacao {
	public static final char TIPO_CREDITO = 'c';
	public static final char TIPO_DEBITO = 'd';

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	@Column(name = "realizada_em")
	@CreationTimestamp
	private LocalDateTime realizadaEm;

	@Column(name = "id_cliente")
	@JsonIgnore
	private int idCliente;

	@Column
	private char tipo;

	@Column
	private long valor;

	@Column
	private String descricao;

	public int getId() {
		return id;
	}

	public LocalDateTime getRealizadaEm() {
		return realizadaEm;
	}

	public int getIdCliente() {
		return idCliente;
	}

	void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public boolean estaValida() {
		if (tipo != TIPO_CREDITO && tipo != TIPO_DEBITO) {
			return false;
		}
		if (valor <= 0) {
			return false;
		}
		if (descricao == null || descricao.length() < 1 || descricao.length() > 10) {
			return false;
		}
		return true;
	}
}
