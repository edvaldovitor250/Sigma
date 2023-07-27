create table aluno(
id bigint not null auto_increment,
nome_aluno varchar(80) not null,

primary key (id)


)engine=InnoDB default charset=utf8;


create table materia(

id bigint not null auto_increment,
nome_materia varchar(80) not null,
numeros_horas INT NOT NULL,


primary key (id)



)engine=InnoDB default charset=utf8;

create table professor(

id bigint not null auto_increment,
nome_professor varchar(80) not null,

primary key (id)

)engine=InnoDB default charset=utf8;
