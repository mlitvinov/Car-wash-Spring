create sequence hibernate_sequence start 4 increment 1;

create table orders
(
    id           int8 not null,
    service varchar(255),
    tel          varchar(255),
    time        varchar(255),
    primary key (id)
);

create table services
(
    id           int8 not null,
    price        int4 not null,
    service varchar(255),
    primary key (id)
);

create table schedule
(
    id   int4 not null,
    time varchar(255),
    primary key (id)
);

alter table if exists services
    add constraint services_user_fk
    foreign key (id) references services;


alter table if exists waiting_list
    add constraint waiting_list_user_fk
    foreign key (id) references waiting_list;


alter table if exists schedule
    add constraint schedule_fk
    foreign key (id) references schedule;

