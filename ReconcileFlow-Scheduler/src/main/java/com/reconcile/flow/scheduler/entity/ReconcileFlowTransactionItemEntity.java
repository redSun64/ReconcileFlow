package com.reconcile.flow.scheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.Arrays;

@DynamicInsert
@Builder
@Entity
@Table(name = "reconcile_flow_transaction_item", schema = "reconcile_flow", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileFlowTransactionItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "tx_id")
    private long txId;
    @Basic
    @Column(name = "service_name")
    private String serviceName;
    @Basic
    @Column(name = "status")
    private Byte status;
    @Basic
    @Column(name = "execute_param")
    private byte[] executeParam;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

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

    public byte[] getExecuteParam() {
        return executeParam;
    }

    public void setExecuteParam(byte[] executeParam) {
        this.executeParam = executeParam;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReconcileFlowTransactionItemEntity that = (ReconcileFlowTransactionItemEntity) o;

        if (id != that.id) return false;
        if (txId != that.txId) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (!Arrays.equals(executeParam, that.executeParam)) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (txId ^ (txId >>> 32));
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(executeParam);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
