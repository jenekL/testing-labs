create table delivery_methods
(
    id   integer unique not null primary key GENERATED ALWAYS AS IDENTITY,
    name varchar(255) unique not null
);

create table suppliers
(
    id                  integer unique not null primary key GENERATED ALWAYS AS IDENTITY,
    company_name        varchar(50) CHECK (length(company_name) > 0),
    first_name          varchar(50) CHECK (length(first_name) > 0),
    last_name           varchar(50) CHECK (length(last_name) > 0),
    fathers_name        varchar(50) CHECK (length(fathers_name) > 0),
    bank_account_number integer not null,
    phone               varchar(15) CHECK (length(phone) > 0),
    fax                 varchar(15) CHECK (length(fax) > 0),
    price               float CHECK (price > 0),
    method              integer not null,
    CONSTRAINT fk_method
        FOREIGN KEY (method)
            REFERENCES delivery_methods (id)
);

create table supply_types
(
    id   integer unique      not null primary key GENERATED ALWAYS AS IDENTITY,
    name varchar(255) unique not null
);

create table supplies
(
    id         integer unique not null primary key GENERATED ALWAYS AS IDENTITY,
    type       integer not null,
    mark       varchar(100) not null,
    delay_time integer CHECK (delay_time > 0),
    price      float CHECK (price > 0),
    CONSTRAINT fk_method
        FOREIGN KEY (type)
            REFERENCES supply_types (id)
);

create table suppliers_supplies
(
    supplier_id integer not null,
    supply_id   integer not null,
    PRIMARY KEY (supplier_id, supply_id),
    CONSTRAINT fk_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES suppliers (id),
    CONSTRAINT fk_supply
        FOREIGN KEY (supply_id)
            REFERENCES supplies (id)
);

insert into delivery_methods (name)
values ('post'),
       ('courier');

insert into supply_types (name)
values ('packets'),
       ('shoes'),
       ('scarfs'),
       ('t-shirts'),
       ('juice');

insert into suppliers (company_name, first_name, last_name, fathers_name, bank_account_number, phone, fax, price,
                       method)
values ('My company', 'John', 'Doe', 'Ivanovich', 1234321, '+380661234561', '+380621231231', 123, 2),
       ('My company 1', 'William', 'Doe', 'Sergeevich', 76312793, '+380661234562', '+380621231232', 124, 1),
       ('My company 2', 'Sebastian', 'Doe', 'Avramovich', 1234326, '+380661234563', '+380621231233', 125, 1),
       ('My company 3', 'Said', 'Doe', 'Ivanovich', 1234329, '+380661234564', '+380621231234', 126, 1),
       ('My company 4', 'Lucas', 'Doe', 'Ivanovich', 123432121, '+380661234565', '+380621231235', 127, 2);

insert into supplies (type, mark, delay_time, price)
VALUES (1, 'bmw', 1, 2),
       (2, 'bentley', 1, 2),
       (3, 'gucci', 1, 2),
       (4, 'balenciaga', 1, 2),
       (5, 'sadi kubani', 1, 2);

insert into suppliers_supplies (supplier_id, supply_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 5);

update suppliers
set bank_account_number = 312312
where company_name LIKE 'My company';

DELETE
FROM suppliers
WHERE company_name LIKE 'My company 1';

