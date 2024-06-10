create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('Линейка', 50), ('Наушники', 5200), ('Шариковая ручка', 8), ('Ластик', 3);
insert into people(name) values ('Людмила'), ('Феофан'), ('Маргарита'), ('Владимир');
insert into devices_people(device_id, people_id) values (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 4), (2, 3);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 4);
insert into devices_people(device_id, people_id) values (4, 1), (4, 2), (4, 4);

select avg(price) avg_price from devices;

select p.name, avg(d.price) 
from devices_people as dp 
join devices d 
on dp.device_id = d.id 
join people p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp 
join devices d 
on dp.device_id = d.id 
join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;

