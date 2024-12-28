package com.reconcile.flow.example.serviceB.repository;

import com.reconcile.flow.example.serviceB.entity.ExampleServiceBAccountOperationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleServiceBAccountOperationEntityRepository extends CrudRepository<ExampleServiceBAccountOperationEntity, Long> {
    @Query("select e from ExampleServiceBAccountOperationEntity e where e.userId = ?1")
    ExampleServiceBAccountOperationEntity findByUserId(Long userId);
}