create table address (
	id bigint primary key,
	street varchar(120) not null,
	city varchar(80) not null,
	state varchar(80) not null,
	postal_code varchar(8) not null,
	country varchar(40) not null
);

create sequence address_seq as bigint
increment by 1
start with 1
minvalue 1
owned by address.id;

create table customer_address (
    customer_id bigint not null,
    address_id bigint not null,

    primary key (customer_id, address_id),

    constraint fk_customer_id foreign key (customer_id) references customer (id),
    constraint fk_address_id foreign key (address_id) references address (id)
);