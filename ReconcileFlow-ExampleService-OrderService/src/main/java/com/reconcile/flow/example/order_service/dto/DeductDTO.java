package com.reconcile.flow.example.order_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DeductDTO {
    private Long userId;
    private Long opId;
    private BigDecimal opAmount;
}
