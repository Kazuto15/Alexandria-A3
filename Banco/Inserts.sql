insert into tb_tipolivros (tipo)
values('Sustentabilidade'),
('Tecnologia'),
('Ciência e Sociedade');

insert into tb_autor(nome,id_livro)
values('Robert C. Martin',1),
('Michael T.Goodrich',2),
('Wes McKinney',3),
('Ricardo Abramovay',4),
('Peter M. Senge',5),
('Lucie Sauvé',6),
('Ildeu de Castro',7),
('Ilya Prigogine',8),
('Rubem Alves',9);

insert into tb_livros(nome,id_tipo)
values('Código Limpo: Habilidades Práticas do Agile Software',2),
('Estrutura de Dados e Algoritmos em Java',2),
('Python para Análise de Dados',2),
('Sustentabilidade: O Que É - O Que Não é',1),
('A Quinta Disciplina: Arte e Prática da Organização que Aprende',1),
('Educação Ambiental: Princípios e Práticas',1),
('Ciência, Tecnologia e Sociedade',3),
('A Nova Aliança: Metamorfose da Ciência',3),
('O Que É Ciência?',3);


insert into tb_Usuario(nome,dt_nascimento,id_livro,id_tipousuario)
values('Arthur Trindade','1996-10-25',8,1),
	  ('Bruno Carvalho ','1987-04-03',2,2),
	  ('Raquel Brito','1994-12-15',6,2);