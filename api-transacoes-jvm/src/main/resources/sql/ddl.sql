DROP TABLE public.clientes;
DROP TABLE public.transacoes;

CREATE TABLE clientes {
	id INT NOT NULL,
	limite BIGINT NOT NULL,
	saldo BIGINT NOT NULL,
	CONSTRAINT clientes_pk PRIMARY KEY (id)
}

CREATE TABLE public.transacoes {
	id INT NOT NULL,
	id_cliente INT NOT NULL,
	realizada_em DATE NOT NULL,
	tipo CHAR(1) NOT NULL,
	valor BIGINT NOT NULL,
	descricao VARCHAR(10) NOT NULL,
	CONSTRAINT clientes_pk PRIMARY KEY (id),
	CONSTRAINT tipo_permitido CHECK (tipo = 'c' OR tipo = 'd'),
	CONSTRAINT ultimas_transacoes_idx INDEX ON (id_cliente ASC, realizada_em DESC)
}


-- Clientes iniciais
INSERT INTO public.clientes
VALUES
	(1, 100000, 0),
	(2, 80000, 0),
	(3, 1000000, 0),
	(4, 10000000, 0)
	(5, 500000, 0);
