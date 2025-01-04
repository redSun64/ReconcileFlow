package com.reconcile.flow.core.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: ReconcileFlowTxDTO
 * @Description: TODO
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 16:37
 */
@Data
@Builder
public class ReconcileFlowTxDTO implements Serializable {
    private String txId;
    private String methodName;
    private String param;
    private String className;
    private String serviceName;
    private Byte status;
    private Long expectedFinishDuration;
}