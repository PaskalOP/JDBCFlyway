alter table project add column MONTH_COUNT bigint;
update project
set MONTH_COUNT = extract (month from age(FINISH_DATE,START_DATE))+ extract(year from age(FINISH_DATE,START_DATE))*12;
