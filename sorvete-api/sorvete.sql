create DATABASE sorvete;

use sorvete;

create TABLE tipo_Sorvete(
	id int primary key auto_increment NOT NULL,
	codigo int NOT NULL,
	tipo text NOT NULL,
	qtd_bola int NOT NULL,
	peso text NOT NULL,
	descricao text NOT NULL,
	valor numeric(10, 2) NOT NULL
);

create TABLE sabor(
	id int primary key auto_increment,
	codigo int NOT NULL,
	nome text NOT NULL,
	descricao text NOT NULL
);

create TABLE sorvete(
	id int  primary key auto_increment,
	codigo int NOT NULL,
	data date NOT NULL,
    tipo_sorvete_id INT,
    sabor_id INT,
    FOREIGN KEY (tipo_sorvete_id) REFERENCES tipo_Sorvete (id),
    FOREIGN KEY (sabor_id) REFERENCES sabor (id)
);
select * from sabor;
select * from sorvete;