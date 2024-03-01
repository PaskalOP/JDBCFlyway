alter table client
add column project_count bigint;

update client set project_count = (
	select count(*) from project where project.CLIENT_ID = client.ID);
