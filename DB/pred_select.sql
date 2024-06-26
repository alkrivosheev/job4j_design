
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
INSERT INTO customers
VALUES (1, 'Иван', 'Петров', 34, 'Москва'),
       (2, 'Максим', 'Сергеев', 67, 'Москва'),
       (3, 'Эдуард', 'Романов', 28, 'Москва'),
       (4, 'Анна', 'Бочкина', 17, 'Москва'),
       (5, 'Илья', 'Сечин', 35, 'Москва');

SELECT * FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 3, 2), (2, 1, 1), (3, 3, 5), (4, 6, 5),
		(5, 2, 4), (6, 3, 2), (7, 4, 2), (8, 7, 4);

SELECT first_name, last_name, age, country 
FROM customers WHERE customers.id NOT IN (SELECT customer_id FROM orders);

