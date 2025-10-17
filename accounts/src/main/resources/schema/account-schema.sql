create table account
(
    id             bigint primary key auto_increment,
    type           varchar(255) not null,
    branch_address varchar(255) not null,
    customer_id    bigint       not null,
    created_at     timestamp    not null,
    created_by     varchar(255) not null,
    updated_at     timestamp    default null,
    updated_by     varchar(255) default null
);

alter table account
    add constraint fk_customer_id_in_account_table
        foreign key (customer_id) references customer (id);

alter table account
    add constraint unique_customer_id_in_account_table
        unique (customer_id);