DROP TABLE IF EXISTS public.clientes;
DROP TABLE IF EXISTS public.transacoes;

CREATE TABLE clientes (
	id INT NOT NULL,
	limite BIGINT NOT NULL,
	saldo BIGINT NOT NULL,
	CONSTRAINT clientes_pk PRIMARY KEY (id)
);

CREATE TABLE public.transacoes (
	id SERIAL NOT NULL,
	id_cliente INT NOT NULL,
	realizada_em TIMESTAMP NOT NULL,
	tipo CHAR(1) NOT NULL,
	valor BIGINT NOT NULL,
	descricao VARCHAR(10) NOT NULL,
	CONSTRAINT transacoes_pk PRIMARY KEY (id),
	CONSTRAINT tipo_permitido CHECK (tipo = 'c' OR tipo = 'd')
);

CREATE INDEX ultimas_transacoes_idx ON public.transacoes (id_cliente ASC, realizada_em DESC);

-- Clientes iniciais
INSERT INTO public.clientes
VALUES
	(1, 100000, 0),
	(2, 80000, 0),
	(3, 1000000, 0),
	(4, 10000000, 0),
	(5, 500000, 0);
