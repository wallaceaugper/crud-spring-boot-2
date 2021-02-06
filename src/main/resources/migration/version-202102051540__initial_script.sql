drop table if exists pessoa;

create table if not exists pessoa (
    id varchar(40) not null primary key,
    nome varchar(255),
    idade int,
    nacionalidade varchar(50)
)