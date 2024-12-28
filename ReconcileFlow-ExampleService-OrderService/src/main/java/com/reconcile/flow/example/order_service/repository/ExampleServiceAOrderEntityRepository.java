package com.reconcile.flow.example.order_service.repository;

import com.reconcile.flow.example.order_service.entity.ExampleServiceAOrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExampleServiceAOrderEntityRepository extends CrudRepository<ExampleServiceAOrderEntity, Long> {
}