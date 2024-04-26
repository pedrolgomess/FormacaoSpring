create table pacientes(
    id  serial primary key,
    nome varchar(100) not null,
    telefone varchar(100) not null unique,
    cpf varchar(6) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null
);