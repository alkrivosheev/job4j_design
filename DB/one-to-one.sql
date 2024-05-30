create table windows(
    id serial primary key,
    serial_num varchar(255),
    name varchar(255)
	);

create table server(
    id serial primary key,
    name varchar(255),
    windows_id int references windows(id) unique
);