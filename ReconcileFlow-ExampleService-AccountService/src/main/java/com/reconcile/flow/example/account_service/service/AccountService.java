package com.reconcile.flow.example.account_service.service;

import com.reconcile.flow.core.annotation.NeedReconcile;
import com.reconcile.flow.core.annotation.ReconcileFlowService;
import com.reconcile.flow.example.account_service.entity.ExampleServiceBAccountEntity;
import com.reconcile.flow.example.account_service.entity.ExampleServiceBAccountOperationEntity;
import com.reconcile.flow.example.account_service.repository.ExampleServiceBAccountEntityRepository;
import com.reconcile.flow.example.account_service.repository.ExampleServiceBAccountOperationEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@ReconcileFlowService
public class AccountService {

    @Resource
    private ExampleServiceBAccountOperationEntityRepository bAccountOperationEntityRepository;
    @Resource
    private ExampleServiceBAccountEntityRepository accountEntityRepository;

    @Transactional
    @NeedReconcile("deduct")
    public void deduct(long userId, long opId, BigDecimal opValue) {
        accountEntityRepository.updateAmountByUserId(userId, opValue);
        ExampleServiceBAccountEntity account = accountEntityRepository.findByUserId(userId);
        if (account.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("amount is negative");
        }
        bAccountOperationEntityRepository.save(ExampleServiceBAccountOperationEntity.builder()
                .userId(userId)
                .opId(opId)
                .opValue(opValue.intValue())
                .build());
    }
}
