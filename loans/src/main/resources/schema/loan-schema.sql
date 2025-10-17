create table loan
(
    id            bigint primary key auto_increment,
    mobile_number varchar(255) not null,
    number        bigint       not null,
    type          varchar(255) not null,
    total         integer      not null,
    repaid        integer      not null,
    remaining     integer      not null,
    created_at    timestamp    not null,
    created_by    varchar(255) not null,
    updated_at    timestamp    default null,
    updated_by    varchar(255) default null
);