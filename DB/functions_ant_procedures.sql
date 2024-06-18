

--Процедура удаляет товар с нулевум остатком в поле count

create
or replace procedure delete_data(p_id integer, p_count integer)
language 'plpgsql'
as $$
    BEGIN
	if p_count <= 0 THEN
        delete from products where id = p_id;
	end if;
    END
$$;

call delete_data(1, 0);

drop procedure delete_data(p_id integer, p_count integer);

--Функция удаляет указанный товар и возвращяет количество оставшегося на складе
create
or replace function f_delete_data(p_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products where id = p_id;
        select into result count(name)
        from products;
        return result;
    end;
$$;

drop function f_update_data(u_count integer, tax float, u_id integer);

select f_delete_data(2);

drop function f_delete_data(p_id integer);




