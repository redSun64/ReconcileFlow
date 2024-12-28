package com.reconcile.flow.scheduler.repository;

import com.reconcile.flow.scheduler.entity.ReconcileFlowTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReconcileFlowTransactionEntityRepository extends CrudRepository<ReconcileFlowTransactionEntity, Long> {
}