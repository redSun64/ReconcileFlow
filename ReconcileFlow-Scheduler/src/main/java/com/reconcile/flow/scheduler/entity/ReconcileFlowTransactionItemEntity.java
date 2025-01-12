package com.reconcile.flow.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "reconcile_flow_transaction_item", schema = "reconcile_flow")
public class ReconcileFlowTransactionItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tx_id", nullable = false)
    private Long txId;

    @Column(name = "service_name", nullable = false, length = 256)
    private String serviceName;

    @Column(name = "class_name", length = 256)
    private String className;

    @Column(name = "method_name", nullable = false, length = 256)
    private String methodName;

    @Column(name = "status")
    private String status;

    @Column(name = "param")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Map<String, Object>> param;

    @Column(name = "expected_finish_duration", nullable = false)
    private Instant expectedFinishDuration;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

}