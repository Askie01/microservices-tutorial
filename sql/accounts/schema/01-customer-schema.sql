create table customer
(
    id            bigint primary key auto_increment,
    name          varchar(255) not null,
    email         varchar(255) not null,
    mobile_number varchar(255) not null,
    created_at    timestamp    not null,
    created_by    varchar(255) not null,
    updated_at    timestamp    default null,
    updated_by    varchar(255) default null
);