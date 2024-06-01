insert into comments(comment) values ('Comment1');
insert into comments(comment) values ('Comment2');
insert into comments(comment) values ('Comment3');

insert into attachs(name) values ('attach1');
insert into attachs(name) values ('attach2');
insert into attachs(name) values ('attach3');

insert into items(name, attachs_id, comments_id) values ('item1', 1, 1);
insert into items(name, attachs_id, comments_id) values ('item2', 2, 1);
insert into items(name, attachs_id, comments_id) values ('item3', 1, 3);


insert into users(name, items_id) values ('Alexey', 1);
insert into users(name, items_id) values ('Ivan', 3);
insert into users(name, items_id) values ('Dmitriy', 2);

insert into roles(name, users_id) values ('role1', 1);
insert into roles(name, users_id) values ('role2', 3);
insert into roles(name, users_id) values ('role3', 2);

insert into rules(name) values ('rule1');
insert into rules(name) values ('rule2');
insert into rules(name) values ('rule3');


insert into rules_in_roles(rules_id, roles_id) values (1, 3);
insert into rules_in_roles(rules_id, roles_id) values (3, 3);
insert into rules_in_roles(rules_id, roles_id) values (2, 1);


insert into states(name, items_id) values ('state1', 2);
insert into states(name, items_id) values ('state2', 1);
insert into states(name, items_id) values ('state3', 2);


insert into categories(name, items_id) values ('category1', 1);
insert into categories(name, items_id) values ('category2', 1);
insert into categories(name, items_id) values ('category3', 3);

select * from users;
