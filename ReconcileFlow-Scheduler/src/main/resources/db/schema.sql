create SCHEMA if not exists reconcile_flow;

-- auto-generated definition
create table reconcile_flow.reconcile_flow_transaction_item
(
    id                       bigint auto_increment
        primary key,
    tx_id                    bigint               not null comment 'transaction 主键 Id',
    service_name             varchar(256)         not null comment '服务名',
    class_name               varchar(256) null,
    method_name              varchar(256)         not null,
    status                   tinyint null comment '完成状态：0 待完成，1 完成，2 失败',
    param                    json null comment '[{"int":1},{"String":"123"}]',
    expected_finish_duration bigint default 60000 not null comment '预期结束时间间隔 ms',
    create_time              datetime null,
    update_time              datetime null
);

