package com.github.wesleyegberto.apitransacoes.clientes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	private int id;

	@Column
	private long limite;

	@Column
	private long saldo;

	public Cliente() {
	}

	public Cliente(int id, long limite, long saldo) {
		this.id = id;
		this.limite = limite;
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public long getLimite() {
		return limite;
	}

	public long getSaldo() {
		return saldo;
	}

	public boolean consegueDebitar(long valor) {
		if (saldo >= valor) {
			return true;
		}
		return limite >= Math.abs(saldo - valor);
	}

	public void credita(long valor) {
		this.saldo += valor;
	}

	public void debita(long valor) {
		if (!consegueDebitar(valor)) {
			throw new IllegalArgumentException("Saldo insuficiente para debitar o valor informado.");
		}
		this.saldo -= valor;
	}
}
