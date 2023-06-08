create table _user (
    id bigint primary key,
    full_name varchar(100) not null,
    email varchar(180) unique not null,
    password varchar(256) not null
);

create sequence _user_sequence as bigint
increment by 50
start with 1
minvalue 1
owned by _user.id;