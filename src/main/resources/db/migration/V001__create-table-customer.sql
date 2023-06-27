create table customer (
    id bigint primary key,
    full_name varchar(100) not null,
    cpf varchar(11) unique not null,
    email varchar(180) unique not null,
    birthdate timestamptz not null,
    created_at timestamptz not null,
    contact_number varchar(11) not null,
    nationality varchar(60) not null
);

create sequence customer_seq as bigint
increment by 1
start with 1
minvalue 1
owned by customer.id;