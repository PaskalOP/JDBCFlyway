insert into project(CLIENT_ID, START_DATE,finish_date) values
(1,'2024/02/16','2024/12/16'),
(2,'2024/02/10','2024/08/10'),
(3,'2024/03/01','2024/04/01'),
(4,'2024/03/12','2024/05/01'),
(5,'2024/03/02','2025/04/01'),
(2,'2024/02/10','2028/02/10'),
(2,'2024/02/15','2027/01/01'),
(3,'2024/04/01','2029/04/01'),
(4,'2024/05/01','2025/05/01'),
(5,'2024/02/18','2024/04/18'),
(1,'2024/02/16','2024/12/16'),
(1,'2024/02/10','2024/08/10'),
(2,'2024/03/01','2024/04/01');

update client set project_count = (
	select count(*) from project where project.CLIENT_ID = client.ID);

update project
    set MONTH_COUNT = extract (month from age(FINISH_DATE,START_DATE))+ extract(year from age(FINISH_DATE,START_DATE))*12;


