# Clever-Bank
Стек:
- Java 17
- Gradle
- PostgreSQL
- JDBC
- Lombok
В проекте была реализована схема работы Clever-Bank со следующими сущностями: Банк, Счёт, Пользователь, Транзакция.
Реализована операции пополнения и снятия средств со счета. Реализована возможность перевода средств другому клиенту Clever-Bank и
клиенту другого банка. При переводе средств в другой банк использовав одну транзакцию

Регулярно, по расписанию (раз в полминуты), проверяется, нужно ли начислять проценты (1% - значение подставляется из конфигурационного файла config.yml) на остаток
счета в конце месяца. Проверка и начисление процентов реализованы асинхронно.

Чеки о произведенных транзакциях сохраняются в папку check, в корне проекта формат .pdf

В проекте используется система автоматической сборки Gradle. Установив ее и все зависимости мы можем приступать к работе.
Для старта приложения используется класс Main в нем находятся 2 реализации создания и закрытия потоков, а также прописан метод testWork()
данный метод поможет создать свой счет и его пополнить, для остальных действий со счетом нужно обращаться к классу TransactionDAO в нем реализованы методы
для работы со счетом: снятие, пополнение, перевод средств (withdrawals(Bill bill, double value), refill(Bill bill, double value), transaction(Bill billSender, Bill billReceiver, double value))
также в пакете DAO мы можем найти остальные классы для работы со счетом и другими базами данных: BankDAO (для работы с таблицей банков), ClientDAO (для осуществления работы с таблицей клиентов),
BillDAO (для работы со счетами пользователей), TransactionDAO (для работы с таблицей транзакций).
Все эти классы имеют в себе CRUD операции заключенные в методах: add(Object obj), getById(int id), getAll(), remove(Object obj), update(int id, Object obj). Данные методы в полной мере помогают выполнять действия 
по управлению счетами, клиентами, банками, транзакциями, удаляя, добавляя, изменяя объекты таблицы.

База данных PostgreSQL (url:jdbc:postgresql://localhost:5432/clever), (username: postgres), (password: 1234): 

CREATE DATABASE clever
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.utf8'
    LC_CTYPE = 'Russian_Russia.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

  ## BankTable:                                                             

  CREATE TABLE IF NOT EXISTS public.bank
(
    bank text COLLATE pg_catalog."default" NOT NULL,
    id bigint NOT NULL,
    CONSTRAINT banks_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bank
    OWNER to postgres;

values:
    "Clever-Bank"	1
    "Bank1"	2
    "Bank2"	3
    "Bank3"	4
    "Bank4"	5
    "Bank5"	6

## ClientTable:

CREATE TABLE IF NOT EXISTS public.client
(
    client text COLLATE pg_catalog."default",
    id bigint NOT NULL DEFAULT 0,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.client
    OWNER to postgres;

values:
"Klimenko V.V."	1
"Poltoran D.A."	2
"Bachilov A.I."	3
"Egorov G.A."	4
"Zuev K.O."	5
"Borisov E.R."	6
"Filipov F.A."	7
"Jukov E.D."	8
" Egorov O.F."	9
"Komarov A.A."	10
"Mushin D.D"	11
"Sidorov H.E."	12
"Lebedeva L.G."	13
"Ivanov I.I."	14
"Nefedova C.I."	15
"Kuskova A.D."	16
"Kazeko D.N."	17
"Klimenko I.V."	18
"Ivanov A.A."	19
"Popov V.A."	20
"Varena A.D."	21
"Ekenova A.F."	22
"fkfk"	23
"Klin G.G."	24
"Hemer A.A."	25


## BillTable:

CREATE TABLE IF NOT EXISTS public.bill
(
    id bigint NOT NULL,
    client bigint NOT NULL DEFAULT 0,
    bank bigint NOT NULL DEFAULT 0,
    value numeric(10,2),
    CONSTRAINT "Account_pkey" PRIMARY KEY (id),
    CONSTRAINT bank_key FOREIGN KEY (bank)
        REFERENCES public.bank (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT client_key FOREIGN KEY (client)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bill
    OWNER to postgres;

values:
1000000001	20	5	243065.50
1000000002	19	4	199993.09
1000000003	18	3	108814.41
1000000004	17	2	85011.26
1000000005	16	1	96346.10
1000000006	15	5	515735.00
1000000007	14	4	5198155.31
1000000008	13	3	71409.46
1000000009	12	2	37745.00
1000000010	11	1	6120.81
1000000011	10	5	75943.43
1000000012	9	4	15868.78
1000000013	8	3	425056.33
1000000014	7	2	277703.49
1000000015	6	1	392185.31
1000000016	5	5	524802.85
1000000017	4	4	642685.16
1000000018	3	3	1118748.22
1000000019	2	2	516868.44
1000000020	1	1	894318.48
1000000021	20	5	513468.03
1000000022	19	4	13601.82
1000000023	18	3	48739.75
1000000024	17	2	60074.66
1000000025	16	1	26070.09
1000000026	15	5	112214.81
1000000027	14	4	5667.44
1000000028	13	3	35138.03
1000000029	12	2	1006533.36
1000000030	11	1	12468.30
1000000031	10	5	513468.03
1000000032	9	4	513468.03
1000000033	8	3	605280.19
1000000034	7	2	5421451.60
1000000035	6	1	513468.03
1000000036	5	5	59479.86
1000000037	4	4	0.12
1000000038	3	3	564497.25
1000000039	2	2	2805.65
1000000040	2	5	71824.71

## TransactionTable:
CREATE TABLE IF NOT EXISTS public.transaction
(
    id bigint NOT NULL,
    type bigint NOT NULL,
    sender bigint NOT NULL,
    receiver bigint NOT NULL,
    value numeric(10,2),
    date date,
    "time" time without time zone,
    CONSTRAINT transaction_pkey PRIMARY KEY (id),
    CONSTRAINT type_key FOREIGN KEY (type)
        REFERENCES public.typeoftransaction (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.transaction
    OWNER to postgres;


## TypeOfTransactionTable:
CREATE TABLE IF NOT EXISTS public.typeoftransaction
(
    id bigint NOT NULL,
    type text COLLATE pg_catalog."default",
    CONSTRAINT "typeOfTransaction_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.typeoftransaction
    OWNER to postgres;

values:
1	"withdrawals"
2	"refill"
3	"transaction"


