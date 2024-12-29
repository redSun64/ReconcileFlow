package com.reconcile.flow.core.domain.service;

import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;

import java.util.List;

/**
 * @className: AnalysisTxDomainService
 * @Description: 分析哪些事务需要补偿，并下发补偿任务
 * @version:
 * @author: red_sun
 * @date: 2024/12/29 16:48
 */
public interface AnalysisTxDomainService {

    List<ReconcileFlowTxDTO> getNeedReconcileTxs();

}