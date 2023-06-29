CREATE TABLE investment (
    id bigint primary key,
    name varchar(80) not null,
    balance decimal(12, 2),
    profitability decimal(4, 2) not null,
    description varchar(120) not null,
    created_at timestamptz not null
);

CREATE SEQUENCE investment_seq as bigint
increment by 1
start with 1
minvalue 1
OWNED BY investment.id;

CREATE TABLE account_investments (
    account_code varchar(8) not null,
    investment_id bigint not null,
    total_balance decimal(12,2) not null,
    created_at timestamptz not null,
    updated_at timestamptz not null,

    primary key (account_code, investment_id),

    constraint fk_account_code foreign key (account_code) references account (code),
    constraint fk_investment_id foreign key (investment_id) references investment (id)
);

