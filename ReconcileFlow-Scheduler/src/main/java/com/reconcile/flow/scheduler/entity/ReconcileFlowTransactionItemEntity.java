package com.reconcile.flow.scheduler.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @className: ReconcileFlowTransactionItemEntity
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 17:33
 */
@Entity
@Table(name = "reconcile_flow_transaction_item", schema = "reconcile_flow", catalog = "")
public class ReconcileFlowTransactionItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "tx_id")
    private long txId;
    @Basic
    @Column(name = "method_name")
    private String methodName;
    @Basic
    @Column(name = "service_name")
    private String serviceName;
    @Basic
    @Column(name = "status")
    private Byte status;
    @Basic
    @Column(name = "expected_finish_duration")
    private byte[] expectedFinishDuration;
    @Basic
    @Column(name = "expected_duration")
    private long expectedDuration;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "finish_time")
    private Timestamp finishTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public byte[] getExpectedFinishDuration() {
        return expectedFinishDuration;
    }

    public void setExpectedFinishDuration(byte[] expectedFinishDuration) {
        this.expectedFinishDuration = expectedFinishDuration;
    }

    public long getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(long expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReconcileFlowTransactionItemEntity that = (ReconcileFlowTransactionItemEntity) o;

        if (id != that.id) return false;
        if (txId != that.txId) return false;
        if (expectedDuration != that.expectedDuration) return false;
        if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (!Arrays.equals(expectedFinishDuration, that.expectedFinishDuration)) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (txId ^ (txId >>> 32));
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(expectedFinishDuration);
        result = 31 * result + (int) (expectedDuration ^ (expectedDuration >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        return result;
    }
}