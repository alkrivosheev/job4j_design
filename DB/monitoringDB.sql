Отставание реплики
select replay_lag from pg_stat_replication;

Количество воркеро фвтовакуума в работе
select count(*) from pg_stat_progress_vacuum;

Количество неактивных слотов репликации
select count(*) from pg_replication_slots where active <> 'true';

Мониторинг работы фонового процесса
select buffers_checkpoint, buffers_clean, buffers_backend from pg_stat_bgwriter;

Транзакции длтельнее 10 мнут
select count(*) from pg_stat_activity
where state = 'active' and query_start < now()-interval '10 minute'
						and datname <> 'postgres'
						and lower(left(application_name, 7)) <> 'pgadmin';