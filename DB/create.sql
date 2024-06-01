create table comments (
    id serial primary key,
    comment text
);

create table attachs (
    id serial primary key,
    name text
);

create table items (
    id serial primary key,
    name text,
    attachs_id int references attachs(id),
    comments_id int references comments(id)
);

create table users (
    id serial primary key,
    name text,
    items_id int references items(id)
);

create table roles (
    id serial primary key,
    name text,
    users_id int references users(id)
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
    name text,
    items_id int references items(id)
);

create table categories (
    id serial primary key,
    name text,
    items_id int references items(id)
);