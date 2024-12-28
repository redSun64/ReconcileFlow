create SCHEMA reconcile_flow;
-- auto-generated definition
create table reconcile_flow.example_service_a_order
(
    id               bigint auto_increment
        primary key,
    order_status     tinyint                            null comment '订单状态',
    order_amount     decimal(10, 2)                     null comment '订单金额',
    order_rec_amount decimal(10, 2)                     null comment '订单待支付金额',
    user_id          bigint                             not null,
    create_time      datetime default CURRENT_TIMESTAMP not null,
    update_time      datetime default CURRENT_TIMESTAMP not null
);

