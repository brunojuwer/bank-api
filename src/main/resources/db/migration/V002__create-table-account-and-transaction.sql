CREATE TABLE account (
    code varchar(8) primary key,
    password varchar(256) not null,
    type varchar(2) not null,
    balance decimal(12, 2) not null,
    created_at timestamptz not null,
    last_login_date timestamptz,
    customer_id bigint not null,

    constraint fk_customer_id foreign key (customer_id) references customer (id)
);

CREATE TABLE transaction (
    id bigint primary key,
    account_code varchar(8) not null,
    operation varchar(30),
    amount decimal(12, 2) not null,
    created_at timestamptz not null
);


CREATE TABLE account_transaction (
    account_code varchar(8) not null,
    transaction_id bigint not null,

    primary key (account_code, transaction_id),

    constraint fk_account_code foreign key (account_code) references account (code),
    constraint fk_transaction_id foreign key (transaction_id) references transaction (id)
);

CREATE SEQUENCE transaction_seq AS bigint
increment by 1
start with 1
minvalue 1
OWNED BY transaction.id;

