CREATE TABLE account (
    id bigint primary key,
    type varchar(30),
    balance decimal(12, 2) not null,
    created_at timestamptz not null
);

CREATE TABLE transaction (
    id bigint primary key,
    operation varchar(30),
    ammount decimal(12, 2) not null,
    created_at timestamptz not null
);


CREATE TABLE _user_account (
    user_id bigint not null,
    account_id bigint not null,

    primary key (user_id, account_id),

    constraint fk_user_id foreign key (user_id) references _user (id),
    constraint fk_account_id foreign key (account_id) references account (id)
);

CREATE TABLE account_transaction (
    account_id bigint not null,
    transaction_id bigint not null,

    primary key (account_id, transaction_id),

    constraint fk_account_id foreign key (account_id) references account (id),
    constraint fk_transaction_id foreign key (transaction_id) references transaction (id)
);


CREATE SEQUENCE account_sequence AS bigint
increment by 50
start with 1
minvalue 1
OWNED BY account.id;

CREATE SEQUENCE transaction_sequence AS bigint
increment by 50
start with 1
minvalue 1
OWNED BY transaction.id;

