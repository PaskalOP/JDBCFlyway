create table client(
ID bigint primary key not null,
name varchar(1000) not null check (length(name)>=2));
