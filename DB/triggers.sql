create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5
        AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

alter table products disable trigger discount_trigger;

drop trigger discount_trigger on products;

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted)
        and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

drop trigger tax_trigger on products;

--Прибавить налог к цене товара, после вставки данных, statement уровень
create
or replace function nalog()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure nalog();

drop trigger nalog_trigger on products;

--Прибавить налог к цене товара, до вставки данных, row уровень
create trigger nalog_trigger_before
    before insert
    on products
    for each row
    execute procedure nalog();

create
or replace function nalog()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price * 1.13;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

drop trigger nalog_trigger_before on products;

--история изменения цены

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create trigger price_history_writer
    after insert
    on products
    for each row
    execute procedure history_writer();

create
or replace function history_writer()
    returns trigger as
$$
    BEGIN
        insert into history_of_price 
		(name, price, date)
VALUES (NEW.name, NEW.price, current_timestamp);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

drop trigger price_history_writer on products;