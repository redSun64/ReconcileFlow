package com.reconcile.flow.scheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Builder
@DynamicInsert
@Entity
@Table(name = "reconcile_flow_transaction", schema = "reconcile_flow", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
public class ReconcileFlowTransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "transaction_name")
    private String transactionName;
    @Basic
    @Column(name = "status")
    private byte status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReconcileFlowTransactionEntity that = (ReconcileFlowTransactionEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (transactionName != null ? !transactionName.equals(that.transactionName) : that.transactionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (transactionName != null ? transactionName.hashCode() : 0);
        result = 31 * result + (int) status;
        return result;
    }
}
