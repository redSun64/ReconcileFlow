package com.reconcile.flow.example.account_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeductDTO {
    private Long userId;
    private Long opId;
    private BigDecimal opAmount;
}
