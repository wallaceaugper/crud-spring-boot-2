alter table stream.pessoa
add column deleted varchar(5);

update stream.pessoa
set deleted = 'false';