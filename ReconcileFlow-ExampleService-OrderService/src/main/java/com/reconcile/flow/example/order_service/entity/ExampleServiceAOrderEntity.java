package com.reconcile.flow.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@DynamicInsert
@Entity
@Table(name = "EXAMPLE_SERVICE_A_ORDER", schema = "INFORMATION_SCHEMA", catalog = "TEST")
public class ExampleServiceAOrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "ORDER_STATUS")
    private Byte orderStatus;
    @Basic
    @Column(name = "USER_ID")
    private long userId;
    @Basic
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;
    @Basic
    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;
    @Basic
    @Column(name = "ORDER_AMOUNT")
    private BigDecimal orderAmount;
    @Basic
    @Column(name = "ORDER_REC_AMOUNT")
    private BigDecimal orderRecAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

        ExampleServiceAOrderEntity that = (ExampleServiceAOrderEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderRecAmount() {
        return orderRecAmount;
    }

    public void setOrderRecAmount(BigDecimal orderRecAmount) {
        this.orderRecAmount = orderRecAmount;
    }
}
