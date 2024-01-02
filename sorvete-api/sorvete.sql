create DATABASE sorvete;

use sorvete;

create TABLE tipo_Sorvete(
	id serial NOT NULL,
	codigo text NOT NULL,
	tipo text NOT NULL,
	qtd_bola integer NOT NULL,
	peso text NOT NULL,
	descricao text NOT NULL,
	valor numeric(10, 2) NOT NULL
);

create TABLE sabor(
	id serial NOT NULL,
	codigo text NOT NULL,
	nome text NOT NULL,
	descricao text NOT NULL
);

create TABLE sorvete(
	id serial NOT NULL,
	codigo integer NOT NULL,
	data date NOT NULL,
	tipo_sorvete_id integer NOT NULL,
	sabor_id integer NOT NULL
);
