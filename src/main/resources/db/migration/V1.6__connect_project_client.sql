alter table project add constraint project_client_fk
foreign key (CLIENT_ID ) references client(ID);