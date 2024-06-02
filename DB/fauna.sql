create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date) VALUES
('African Elephant', 25550, '1797-03-01'),
('Bald Eagle', 9125, '1782-06-20'),
('Blue Whale', 29200, '1820-02-05'),
('Koala', 4380, '1803-01-26'),
('Giant Panda', 7300, '1869-03-11'),
('Green Turtle', 36500, '1858-11-24'),
('House Sparrow', 4380, '1758-10-10'),
('Komodo Dragon', 9130, '1910-12-05'),
('Mountain Gorilla', 10950, '1902-10-17'),
('Polar Bear', 9125, '1774-06-11'),
('Red Fox', 3650, '1758-02-15'),
('Sea Otter', 7300, '1741-10-25'),
('Siberian Tiger', 9130, '1926-01-01'),
('Snow Leopard', 7300, '1775-12-04'),
('Tasmanian Devil', 2555, '1808-05-07'),
('Atlantic Puffin', 10950, '1593-07-11'),
('Emperor Penguin', 7300, '1844-10-17'),
('Giant Tortoise', 58400, '1535-09-21'),
('Golden Eagle', 18250, '1789-05-25'),
('Great White Shark', 25550, '1839-04-08'),
('Humpback Whale', 18250, '1756-12-05'),
('King Cobra', 7300, '1836-06-30'),
('Narwhal', 14600, '1705-04-21'),
('Okapi fish', 9130, '1901-08-24'),
('Orangutan', 14600, '1779-05-01'),
('Peregrine Falcon', 9125, '1771-06-10'),
('Platypus', 10950, '1799-11-02'),
('Red Kangaroo', 5475, '1824-09-22'),
('Rhinoceros', 14600, '1741-10-10'),
('Snowy Owl', 3650, '1758-07-11');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 21000
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
