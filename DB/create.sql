create table users (
    id serial primary key,
    name text
);

create table roles (
    id serial primary key,
    name text
);

create table rules (
    id serial primary key,
    name text
);

create table items (
    id serial primary key,
    name text
);

create table comments (
    id serial primary key,
    name text
);

create table attachs (
    id serial primary key,
    name text
);

create table states (
    id serial primary key,
    name text
);

create table categories (
    id serial primary key,
    name text
);