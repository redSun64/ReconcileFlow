package com.reconcile.flow.example.serviceB.repository;

import com.reconcile.flow.example.serviceB.entity.ExampleServiceBAccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface ExampleServiceBAccountEntityRepository extends CrudRepository<ExampleServiceBAccountEntity, Long> {
    @Query("select e from ExampleServiceBAccountEntity e where e.userId = ?1")
    ExampleServiceBAccountEntity findByUserId(Long userId);

    @Modifying
    @Query("update ExampleServiceBAccountEntity e set e.amount = e.amount + ?2 where e.userId = ?1")
    int updateAmountByUserId(Long userId, @Param("opAmount") BigDecimal opAmount);
}