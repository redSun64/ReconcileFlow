package com.reconcile.flow.core.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @className: ReconcileFlowTxDTO
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 16:37
 */
@Data
@Builder
public class ReconcileFlowTxDTO {
    private String txId;
    private String methodName;
    private String param;
    private String serviceName;
    private Byte status;
    private Long expectedFinishDuration;
}