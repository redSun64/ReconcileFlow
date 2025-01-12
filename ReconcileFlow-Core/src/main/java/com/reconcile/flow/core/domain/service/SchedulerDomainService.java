package com.reconcile.flow.core.domain.service;

import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;
import com.reconcile.flow.core.domain.dto.RegisterServiceDTO;

public interface SchedulerDomainService {

    Long addOrUpdateTransaction(ReconcileFlowTxDTO txDTO);

    void registerService(RegisterServiceDTO registerServiceDTO);
}
