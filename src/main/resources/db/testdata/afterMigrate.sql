set foreign_key_checks = 0;

delete from aluno;
delete from professor;
delete from materia;


set foreign_key_checks = 1;

alter table aluno auto_increment = 1;
alter table professor auto_increment = 1;
alter table materia auto_increment = 1;

insert into materia(id, nome_materia, numeros_horas) values (1, 'Matematica', 30);
insert into materia(id, nome_materia, numeros_horas) values (2, 'Historia', 10);
-- Add more INSERT statements as needed for other rows


insert into professor (id,nome_professor) values (1,'Marcela Maia');
insert into professor (id,nome_professor) values (2,'Douglas Carlos');


insert into aluno (id,nome_aluno) values (1,'Maicon Frascio');
insert into aluno (id,nome_aluno) values (2,'Gilson Silva');
