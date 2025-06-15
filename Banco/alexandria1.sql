drop database if exists alexandria1;
create database alexandria1;
use alexandria1;

create table usuario(
	id int auto_increment primary key,
    nome varchar(50),
    senha varchar(30),
    dataNasc date
);
create table admin(
	id int primary key auto_increment,
    nome varchar(50),
    email varchar(100),
    senha varchar(30)
);

create table tipoLivros (
id int  auto_increment PRIMARY KEY,
tipo varchar (30) NOT NULL
);

create table livros(
id int auto_increment PRIMARY KEY,
nome varchar (100) NOT NULL,
autor varchar(100),
id_tipo int, 
usuario_id int,
FOREIGN KEY (id_tipo) REFERENCES tipoLivros(id),
foreign key (usuario_id) references usuario(id)
);



select * from usuario;
select * from admin;
select * from livros;

SELECT * FROM usuario WHERE id = 1;
