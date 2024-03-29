create table _group (
    id bigint primary key,
    name varchar(80) not null unique
);

create table permission (
    id bigint primary key,
    name varchar(80) not null,
    description varchar(180) not null
);

create sequence _group_sequence as bigint
increment by 1
start with 1
minvalue 1
owned by _group.id;

create sequence permission_sequence as bigint
increment by 1
start with 1
minvalue 1
owned by permission.id;

create table _group_permission (
    group_id bigint not null,
    permission_id bigint not null,

    primary key(group_id, permission_id),
    constraint fk_group_id foreign key (group_id) references _group (id),
    constraint fk_permission_id foreign key (permission_id) references permission (id)
);

create table employee_group (
    emplyoee_id bigint not null,
    group_id bigint not null,

    primary key(emplyoee_id, group_id),
    constraint fk_empolyee_id foreign key (user_id) references emplyoee (id),
    constraint fk_group_id foreign key (group_id) references _group (id)
);