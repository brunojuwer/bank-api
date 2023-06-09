create table address (
	id bigint primary key,
	street varchar(120) not null,
	city varchar(80) not null,
	state varchar(80) not null,
	postal_code varchar(8) not null,
	country varchar(40) not null
);

alter table _user add column address_id bigint;
alter table _user add constraint fk_address foreign key (address_id) references address (id);