create table servers(
    id serial primary key,
    name text,
    invNum int
);

create table ipAddress(
    id serial primary key,
    ip varchar(15),
    server_id int references servers(id)
);

insert into servers(name, invNum) values ('database1', 123456);
insert into servers(name, invNum) values ('database2', 123457);
insert into servers(name, invNum) values ('domain controller', 123458);

insert into ipAddress(ip, server_id) values ('192.168.1.3', 1);
insert into ipAddress(ip, server_id) values ('192.168.1.4', 1);
insert into ipAddress(ip, server_id) values ('192.168.1.5', 2);
insert into ipAddress(ip, server_id) values ('192.168.1.1', 3);
insert into ipAddress(ip) values ('192.168.1.6');



select * from servers s inner join ipAddress ip on ip.server_id = s.id;
select s.name, s.invnum, ip.ip from servers s join ipAddress ip on ip.server_id = s.id;
select s.name as "Имя сервера", s.invnum as "Инвентарный номер", ip.ip as "IP Адрес" from servers s join ipAddress ip on ip.server_id = s.id;







