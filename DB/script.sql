create table area51(
	id serial primary key,
	cpuName text,
	memory int,
	ipv4Address varchar(15),
	production boolean
);

insert into area51(cpuName, memory, ipv4Address, production)
	values('IntelCore2Duo', 256, '128.15.23.75', true);

select * from area51;

update area51 set cpuName = 'IntelZeon';

delete from area51;

