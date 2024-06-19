DUMP / RESTORE

Создание резервной копии в формате custome
# /opt/pgpro/ent-16/bin/pg_dump -h 127.0.0.1 -U postgres -F c -f /home/orders_custome orders

Создание резервной копии в формате plain (текст)
# /opt/pgpro/ent-16/bin/pg_dump -h 127.0.0.1 -U postgres -F p -f /home/orders_plane orders

Создание резервной копии в формате directory в два потока
# /opt/pgpro/ent-16/bin/pg_dump -h 127.0.0.1 -U postgres -F d -f /home/orders_dir orders -j 2

RESTORE

Создаем новую БД, куда будем разворачивать архивы (создаю на втором инстансе)
# /opt/pgpro/ent-16/bin/psql -h 127.0.0.1 -p 5433 -U postgres -t -A -q -c 
"CREATE DATABASE DB2 WITH OWNER = postgres ENCODING = 'UTF8' TABLESPACE = default
 LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8' CONNECTION LIMIT = -1"

Разворачиваем бэкап в базу db2  второго инстанса (port 5433) созданную методом custom в два потока
# /opt/pgpro/ent-16/bin/pg_restore -h 127.0.0.1 -p 5433 -U postgres -d db2 /home/orders_custome -j 2

Разворачиваем бэкап в базу db2  второго инстанса (port 5433) созданную методом directory в два потока
# /opt/pgpro/ent-16/bin/pg_restore -h 127.0.0.1 -p 5433 -U postgres -d db2 /home/orders_dir -j 2

Разворачиваем бэкап в базу db2  второго инстанса (port 5433) созданную методом plain
# /opt/pgpro/ent-16/bin/psql -h 127.0.0.1 -p 5433 -U postgres --dbname db2 -f /home/odmin/orders_plane
(разворачивается черех psql т.к. формально не является архивом)