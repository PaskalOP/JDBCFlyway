create table  project_worker(
PROJECT_ID bigint not null,
WORKER_ID bigint not null,

PRIMARY KEY (PROJECT_ID, WORKER_ID),
FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);