create table roles (
    id serial primary key,
    name text
);

create table rules (
    id serial primary key,
    name text
);

create table rules_in_roles (
    id serial primary key,
    rules_id int references rules(id),
    roles_id int references roles(id)
);

create table states (
    id serial primary key,
    name text
);

create table categories (
    id serial primary key,
    name text
);

create table users (
    id serial primary key,
    name text,
    roles_id int references roles(id)
);

create table items (
    id serial primary key,
    name text,
    users_id int references users(id),
    categories_id int references categories(id),
	states_id int references states(id)
);

create table comments (
    id serial primary key,
    comment text,
	items_id int references items(id)
);

create table attachs (
    id serial primary key,
    name text,
	items_id int references items(id)
);







