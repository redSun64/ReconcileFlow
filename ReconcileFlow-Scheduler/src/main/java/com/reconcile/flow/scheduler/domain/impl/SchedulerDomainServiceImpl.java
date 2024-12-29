package com.reconcile.flow.scheduler.domain.impl;

import com.reconcile.flow.core.domain.dto.ReconcileFlowTxDTO;
import com.reconcile.flow.core.domain.dto.RegisterServiceDTO;
import com.reconcile.flow.core.domain.service.SchedulerDomainService;
import com.reconcile.flow.scheduler.repository.ReconcileFlowTransactionItemEntityRepository;
import com.xxl.rpc.core.remoting.provider.annotation.XxlRpcService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
@XxlRpcService
public class SchedulerDomainServiceImpl implements SchedulerDomainService {

    @Resource
    private ReconcileFlowTransactionItemEntityRepository reconcileFlowTransactionEntityRepository;


    @Override
    public void addTransaction(ReconcileFlowTxDTO txDTO) {

    }

    @Override
    public void registerService(RegisterServiceDTO registerServiceDTO) {

    }
}
