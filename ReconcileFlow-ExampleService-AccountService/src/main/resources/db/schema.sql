create SCHEMA if not exists reconcile_flow;

create table reconcile_flow.example_service_b_account
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                             not null,
    amount      decimal                            not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null
);

create table reconcile_flow.example_service_b_account_operation
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                             not null,
    op_id       bigint                             not null,
    op_value    decimal                            not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null
);



