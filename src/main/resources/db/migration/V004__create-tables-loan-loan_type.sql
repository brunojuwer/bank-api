CREATE TABLE loan (
    id bigint primary key,
    total_amount decimal(12, 2) not null,
    remaining_amount decimal(12, 2) not null,
    interest decimal(4, 2) not null,
    description varchar(256) not null,
    status varchar(20) not null,
    issued_at timestamptz not null,
    finished_at timestamptz not null,
    loan_type_id bigint not null
);

CREATE TABLE loan_type (
    id bigint primary key,
    name varchar(30) not null,
    description varchar(180) not null,
    loan_id bigint not null,

    CONSTRAINT fk_loan_id FOREIGN KEY (loan_id) REFERENCES loan (id)
);



CREATE TABLE account_loan (
    account_code varchar(8) not null,
    loan_id bigint not null,

    primary key(account_code, loan_id),

    constraint fk_account_code foreign key (account_code) references account (code),
    constraint fk_loan_id foreign key (loan_id) references loan (id)
);


CREATE SEQUENCE loan_seq as bigint
INCREMENT BY 1
START WITH 1
MINVALUE 1
OWNED BY loan.id;

CREATE SEQUENCE loan_type_seq as bigint
INCREMENT BY 1
START WITH 1
MINVALUE 1
OWNED BY loan_type.id;