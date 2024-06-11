create table type
(
    id   serial primary key,
    name varchar(255)
);

create table product
(
    id    serial primary key,
    name  varchar(255),
	type_id int references type (id),
	expired_date date,
    price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('СОКИ'), ('РЫБА'), ('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price) values 
	('Сыр плавленный', 1, '2024-06-15', 156.18), ('Сыр моцарелла', 1, '2024-06-10', 148.15),
	('Молоко Лианозовское', 2, '2024-06-14', 96.00), ('Тан', 2, '2024-07-05', 115.50),
	('Сок Апельсиновый', 3, '2024-08-23', 150.00), ('Сок Яблочный', 3, '2024-08-24', 155.00),
	('Минтай', 4, '2024-06-15', 430.00), ('Кальмар', 4, '2024-06-23', 325.45),
	('Мороженое фруктовое', 5, '2024-06-20', 120.00), ('Мороженое пломбир', 5, '2024-07-3', 430.00);

select * from product where name LIKE 'Мороженое%';

select * from product where expired_date < CURRENT_DATE;

select * from product where price = (select max(price) from product);

select t.name "имя_типа", count(p.name) "количество" from type t join product p on p.type_id = t.id GROUP BY t.name;

select p.name, p.expired_date, p.price from type t join product p on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name from type t join product p on p.type_id = t.id GROUP BY t.name having 
	(select count(p.name) group by t.name) < 10;

select t.name "тип", p.name "продукт" from type t join product p on p.type_id = t.id ORDER BY t.name;



