create sequence workers_seq start 1;
create sequence clients_seq start 1;
create sequence projects_seq start 1;

alter table worker alter column ID set default nextval('workers_seq');
alter table client alter column ID set default nextval('clients_seq');
alter table project alter column ID set default nextval('projects_seq');