--Для настройки репликации сначала проверим и создадим слоты репликации
--проверка
select * from pg_replication_slots;
--создание
select pg_create_physical_replication_slot('rep');
--удаление
select pg_drop_replication_slot ('rep');

--заливаем свежий слепок мастера в реплику
# su postgres -c '/opt/pgpro/ent-16/bin/pg_basebackup -h 127.0.0.1 -p 5432 -D /var/lib/pgpro/ent-16/data2 -U postgres -P --checkpoint=fast'

-- затем в data3/postgresql.conf меняем port на 5434
-- в катлоге data3 создаем файл standby.signal  и даем права postgres/postgres 
--(при старте базы data3 этот фай говорит базе работать в режиме реплики)

--делаем настройку реплики на подключение к мастеру в файле  data3/postgresql.conf 
primary_conninfo = 'host=127.0.0.1 port=5432 user=postgres' 
primary_slot_name = 'rep'
promote_trigger_file = startmaster --(работал до 16 версии postgres, сейчас безполезен)
recovery_target_timeline = 'latest'-- указатель насколько отставать от мастера, lates-не отставать, -1h отставать на час
max_standby_streaming_delay = 3600s --ждать долгую транзакцию максимум 1 час

--стартуем сервер реплики
 su postgres -c '/opt/pgpro/ent-16/bin/pg_ctl -D /var/lib/pgpro/ent-16/data2  start'

--мониторинг отставания реплики
select * from pg_stat_replication;

--ПРОЦЕДУРА ПЕРЕКЛЮЧЕНИЯ мастера и реплики
1. Останавливаем мастера
2. Переключаем реплику в режим мастера
--чтобы реплику превратить в мастера нужно либо вызвать команду # pg_ctl promote 
-- либо вызвать процедуру select * from pg_promote()
3. Базу бывшего мастера(БМ) можно удалить и сделать ее репликой
	- либо сприменить команду pg_rewind, отматывающую линиб времени БМ до линии времени бывшей реплики(БР)
	-либо в PRO версии есть утилита ketchUP (доналивка) которая изменения в файлах БР применяет на файлах БМ
4. ВАЖНО!! -на новом ведущем нужно удалить файл standby.signal (иначе при перезапуске он будет опять репликой)
	-На новом ведущем нужно сохранить все WAL файлы (wal_keep_segments = 10000(80GB), 
	если планируем догонять бывший мастер утилитами pg_rewind или ketchUP
