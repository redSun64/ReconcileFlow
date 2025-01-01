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

-- auto-generated definition
create table reconcile_flow_transaction_item
(
    id                       bigint auto_increment
        primary key,
    tx_id                    bigint               not null comment 'transaction 主键 Id',
    method_name              varchar(256)         not null,
    service_name             varchar(256)         not null comment '服务名',
    status                   tinyint null comment '完成状态：0 待完成，1 完成，2 失败',
    param                    json null,
    expected_finish_duration blob null comment '执行参数',
    expected_duration        bigint default 60000 not null comment '预期结束时间间隔 ms',
    create_time              datetime null,
    update_time              datetime null
);




