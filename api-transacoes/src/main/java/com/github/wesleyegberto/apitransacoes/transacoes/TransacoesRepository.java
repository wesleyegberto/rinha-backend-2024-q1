package com.github.wesleyegberto.apitransacoes.transacoes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends CrudRepository<Transacao, Integer> {
	@Query(
		value = """
		SELECT
		new com.github.wesleyegberto.apitransacoes.transacoes.TransacaoResumida(realizadaEm, tipo, valor, descricao)
		FROM Transacao
		WHERE idCliente = ?1 ORDER BY realizadaEm DESC LIMIT 10
		"""
	)
	Iterable<TransacaoResumida> buscaUltimasTransacoes(int idCliente);
}
