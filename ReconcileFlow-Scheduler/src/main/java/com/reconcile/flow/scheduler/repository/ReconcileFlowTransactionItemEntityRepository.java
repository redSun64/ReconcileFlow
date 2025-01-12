package com.reconcile.flow.scheduler.repository;

import com.reconcile.flow.scheduler.entity.ReconcileFlowTransactionItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public interface ReconcileFlowTransactionItemEntityRepository extends CrudRepository<ReconcileFlowTransactionItemEntity, Long> {
    @Query("select r from ReconcileFlowTransactionItemEntity r where r.txId = ?1")
    ReconcileFlowTransactionItemEntity selectByTxId(Long txId);

    @Query("select r from ReconcileFlowTransactionItemEntity r where r.expectedFinishDuration > ?1")
    List<ReconcileFlowTransactionItemEntity> findByExpectedFinishDurationGreaterThan(Instant expectedFinishDuration);

    @Query("select r from ReconcileFlowTransactionItemEntity r where r.expectedFinishDuration < ?1 and r.status in ?2")
    List<ReconcileFlowTransactionItemEntity> findByExpectedFinishDurationLessThanAndStatusIn(Instant expectedFinishDuration, Collection<String> statuses);
}