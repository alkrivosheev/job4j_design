create table iPAddress(
    id serial primary key,
    ipAdr varchar(15)
);

create table servers(
    id serial primary key,
    name varchar(255),
    iPAddress_id int references iPAddress(id)
);

insert into iPAddress(ipAdr) values ('10.10.12.18');
insert into iPAddress(ipAdr) values ('10.10.12.19');
insert into iPAddress(ipAdr) values ('10.10.12.20');
insert into servers(name, position_id) VALUES ('Database1', 1);
insert into servers(name, position_id) VALUES ('Database1', 3);

select * from servers;

select * from servers where id in (select id from iPAddress);
