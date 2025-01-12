package com.reconcile.flow.example.order_service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @className: OrderDTO
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2025/1/12 23:10
 */
@Data
public class OrderDTO implements Serializable {

    private long id;
    private String orderStatus;
    private long userId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private BigDecimal orderAmount;
    private BigDecimal orderRecAmount;
}