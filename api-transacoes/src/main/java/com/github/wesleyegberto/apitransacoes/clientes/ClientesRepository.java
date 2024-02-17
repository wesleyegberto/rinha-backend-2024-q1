package com.github.wesleyegberto.apitransacoes.clientes;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends CrudRepository<Cliente, Integer> {
	@Modifying
	@Query(
		value = "UPDATE clientes SET saldo = saldo + ?2 WHERE id = ?1",
		nativeQuery = true
	)
	void credita(int idCliente, long valor);

	@Modifying
	@Query(
		value = "UPDATE clientes SET saldo = saldo - ?2 WHERE id = ?1 AND (saldo >= ?2 OR limite >= ABS(saldo - ?2))",
		nativeQuery = true
	)
	void debita(int idCliente, long valor);
}
