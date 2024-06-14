CREATE TABLE car_bodies (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE car_engines (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE car_transmissions (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL
);


CREATE TABLE cars (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies (name)
VALUES
('Sedan'), ('Coupe'), ('SUV'), ('Hatchback'), ('Convertible'), ('Pickup'),
('Minivan'), ('Station Wagon'), ('Crossover'), ('Roadster');

INSERT INTO car_engines (name)
VALUES
('Inline-4'), ('V6'), ('V8'), ('Electric'), ('Гибрид'), ('Turbocharged Inline-4'),
('Дизель'), ('Турбированный V6'), ('W12'), ('Flat-6');

INSERT INTO car_transmissions (name)
VALUES
('Ручная'), ('Автоматическая'), ('CVT'), ('Dual-Clutch'), ('Таптроник'), ('Semi-Automatic'),
('8-ступенчатая автоматическая'),('6-ступенчатая ручная'), ('7-ступенчатая автоматическая'), ('9-ступенчатая автоматическая');

INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES
('Хавал', null, 1, 2), ('Шевроле', 2, 2, null), ('Мерседес', 3, 3, 3), ('Додж', 4, 4, 4), ('Черри', 5, null, 5),
('Ауди', 6, 6, 6), ('Митсубиси', 7, 7, 7), ('Мазератти', null, 8, 8), ('Трактор', 9, null, 9), ('Бугатти', 10, 10, 10),
('Лада', 1, 2, 3), ('Опель', 2, 3, 4), ('Фольксваген', 3, 4, 5), ('Субару', 4, 5, 6), ('Нива', 5, 6, 7);

SELECT c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name from cars c
    LEFT JOIN car_bodies cb ON cb.id = c.body_id
	LEFT JOIN car_engines ce ON ce.id = c.engine_id
	LEFT JOIN car_transmissions ct ON ct.id = c.transmission_id;

SELECT cb.name
FROM car_bodies cb
LEFT JOIN cars c ON cb.id = c.body_id
WHERE c.id IS NULL;

SELECT ce.name
FROM car_engines ce
LEFT JOIN cars c ON ce.id = c.engine_id
WHERE c.id IS NULL;

SELECT ct.name
FROM car_transmissions ct
LEFT JOIN cars c ON ct.id = c.transmission_id
WHERE c.id IS NULL;