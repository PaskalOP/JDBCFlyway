create type level_worker as enum('Trainee','Junior','Middle','Senior');
create table worker(
ID bigint primary key not null,
name varchar(1000) not null check (length(name)>=2),
birthday DATE check (birthday>='1900/01/01'),
level level_worker not null,
selary integer check(selary>=100 and selary<=100000));