create table users(
     id serial primary key,
     name varchar(255)
 );
 
 create table servers(
     id serial primary key,
     name varchar(255)
 );
 
 create table server_in_use(
     id serial primary key,
     user_id int references users(id),
     server_id int references servers(id)
 );

insert into users(name) values ('User1');
insert into users(name) values ('User2');
insert into users(name) values ('User3');

insert into servers(name) values ('DataTier1');
insert into servers(name) values ('DataTier2');
insert into servers(name) values ('DataTier3');

insert into server_in_use(user_id, server_id) values (1, 3);
insert into server_in_use(user_id, server_id) values (1, 1);
insert into server_in_use(user_id, server_id) values (1, 2);
insert into server_in_use(user_id, server_id) values (2, 2);
insert into server_in_use(user_id, server_id) values (3, 2);
insert into server_in_use(user_id, server_id) values (3, 1);

