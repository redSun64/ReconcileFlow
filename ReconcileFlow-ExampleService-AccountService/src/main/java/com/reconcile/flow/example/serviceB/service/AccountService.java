package com.reconcile.flow.example.serviceB.service;

import com.reconcile.flow.example.serviceB.entity.ExampleServiceBAccountEntity;
import com.reconcile.flow.example.serviceB.entity.ExampleServiceBAccountOperationEntity;
import com.reconcile.flow.example.serviceB.repository.ExampleServiceBAccountEntityRepository;
import com.reconcile.flow.example.serviceB.repository.ExampleServiceBAccountOperationEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Resource
    private ExampleServiceBAccountOperationEntityRepository bAccountOperationEntityRepository;
    @Resource
    private ExampleServiceBAccountEntityRepository accountEntityRepository;

    @Transactional
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
