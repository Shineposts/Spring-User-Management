show tables;
select * from country_master;
select * from state_master;
select * from city_master;
insert into country_master values(1,'India');
insert into country_master values(2,'USA');

insert into state_master(state_id, country_id, state_name) values(1, 1,'TN');
insert into state_master(state_id, country_id, state_name) values(2, 1,'TG');
insert into state_master(state_id, country_id, state_name) values(3, 1,'AP');

insert into state_master(state_id, country_id, state_name) values(4, 2,'NY');
insert into state_master(state_id, country_id, state_name) values(5, 2,'VA');


insert into city_master(city_id, city_name, state_id) values(1, 'Thrichy', 1);
insert into city_master(city_id, city_name, state_id) values(2, 'Sivaganga', 1);
insert into city_master(city_id, city_name, state_id) values(3, 'Paramakudi', 1);

insert into city_master(city_id, city_name, state_id) values(4, 'HYD', 2);
insert into city_master(city_id, city_name, state_id) values(5, 'Khamam', 2);

insert into city_master(city_id, city_name, state_id) values(6, 'Vijayawada',3);
insert into city_master(city_id, city_name, state_id) values(7, 'Thirupathi', 3);

insert into state_master(state_id, country_id, state_name) values(4, 2,'NY');
insert into state_master(state_id, country_id, state_name) values(5, 2,'VA');

insert into city_master(city_id, city_name, state_id) values(8, 'NYC', 4);
insert into city_master(city_id, city_name, state_id) values(9, 'Queens', 4);

insert into city_master(city_id, city_name, state_id) values(10, 'Ashburn', 5);
insert into city_master(city_id, city_name, state_id) values(11, 'Herndon', 5);
insert into city_master(city_id, city_name, state_id) values(12, 'Brambleton', 5);