package com.reconcile.flow.scheduler.domain.impl;

import com.reconcile.flow.core.constants.ReconcileFlowConstants;
import com.reconcile.flow.scheduler.domain.SchedulerDomainService;
import com.reconcile.flow.scheduler.entity.ReconcileFlowTransactionEntity;
import com.reconcile.flow.scheduler.repository.ReconcileFlowTransactionEntityRepository;
import com.xxl.rpc.core.remoting.provider.annotation.XxlRpcService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
@XxlRpcService
public class SchedulerDomainServiceImpl implements SchedulerDomainService {

    @Resource
    private ReconcileFlowTransactionEntityRepository reconcileFlowTransactionEntityRepository;

}
