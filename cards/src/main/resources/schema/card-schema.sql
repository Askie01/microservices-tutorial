create table card
(
    id            bigint primary key auto_increment,
    mobile_number varchar(255) not null,
    number        bigint       not null,
    type          varchar(255) not null,
    balance       integer      not null,
    debt          integer      not null,
    money_limit   integer      not null,
    created_at    timestamp    not null,
    created_by    varchar(255) not null,
    updated_at    timestamp    default null,
    updated_by    varchar(255) default null
);