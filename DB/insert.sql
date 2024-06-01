insert into roles(name) values ('role1');
insert into roles(name) values ('role2');
insert into roles(name) values ('role3');

insert into rules(name) values ('rule1');
insert into rules(name) values ('rule2');
insert into rules(name) values ('rule3');

insert into rules_in_roles(rules_id, roles_id) values (1, 3);
insert into rules_in_roles(rules_id, roles_id) values (3, 3);
insert into rules_in_roles(rules_id, roles_id) values (2, 1);

insert into states(name) values ('state1');
insert into states(name) values ('state2');
insert into states(name) values ('state3');

insert into categories(name) values ('category1');
insert into categories(name) values ('category2');
insert into categories(name) values ('category3');

insert into users(name, roles_id) values ('Alexey', 1);
insert into users(name, roles_id) values ('Ivan', 3);
insert into users(name, roles_id) values ('Dmitriy', 2);

insert into items(name, users_id, categories_id, states_id) values ('item1', 1, 1, 3);
insert into items(name, users_id, categories_id, states_id) values ('item2', 2, 1, 1);
insert into items(name, users_id, categories_id, states_id) values ('item3', 1, 3, 2);

insert into comments(comment, items_id) values ('Comment1', 1);
insert into comments(comment, items_id) values ('Comment2', 3);
insert into comments(comment, items_id) values ('Comment3', 2);

insert into attachs(name, items_id) values ('attach1', 1);
insert into attachs(name, items_id) values ('attach2', 3);
insert into attachs(name, items_id) values ('attach3', 1);

select * from items;
