create table consultas(
    id  serial primary key,
    medico_id bigint not null,
    paciente_id bigint not null,
    data Timestamp not null
);