create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id       serial primary key,
    name     varchar(255),
    department_id int references departments (id)
);

insert into departments(name)
values ('Daprtment 1'), ('Daprtment 2'), ('Daprtment 3'), ('Daprtment 4'), ('Daprtment 5');

insert into employees(name, department_id)
values ('employee 1', 1), ('employee 2', 2), ('employee 3', null), ('employee 4', null), ('employee 5', 2);

select * from employees e left join departments d on e.department_id = d.id;

select * from departments d
         left join employees e on d.id = e.department_id;
select * from employees e
         left join departments d on e.department_id = d.id;
select * from departments d
         right join employees e on e.department_id = d.id;
select * from departments d
         left join employees e on d.id = e.department_id;
select * from employees e
         right join departments d on e.department_id = d.id;
select * from employees e
         full join departments d on e.department_id = d.id;

select * from employees e
         left join departments d on e.department_id = d.id
union
select * from employees e
         right join departments d on e.department_id = d.id;

select * from employees e
         cross join departments d;


create table teens
(
    id   serial primary key,
    name varchar(255),
	gender varchar(1)
);
insert into teens(name, gender)
values ('Вася', 'm'), ('Таня', 'f'), ('Зина', 'f'), ('Петя', 'm'), ('Лена', 'f');

SELECT t1.name AS male, t2.name AS female
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender = 'm' AND t2.gender = 'f';

