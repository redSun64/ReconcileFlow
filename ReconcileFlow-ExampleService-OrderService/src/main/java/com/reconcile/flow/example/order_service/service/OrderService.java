package com.reconcile.flow.example.order_service.service;

import com.reconcile.flow.core.annotation.NeedReconcile;
import com.reconcile.flow.core.annotation.ReconcileFlowService;
import com.reconcile.flow.example.order_service.dto.DeductDTO;
import com.reconcile.flow.example.order_service.entity.ExampleServiceAOrderEntity;
import com.reconcile.flow.example.order_service.infrastruction.AccountServiceClient;
import com.reconcile.flow.example.order_service.repository.ExampleServiceAOrderEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

@Service
@ReconcileFlowService
public class OrderService {

    @Resource
    private ExampleServiceAOrderEntityRepository orderEntityRepository;

    @Resource
    private AccountServiceClient accountService;

    @Transactional
    public ExampleServiceAOrderEntity addOrder(ExampleServiceAOrderEntity order) {
        order.setOrderStatus((byte) 1);
        order = orderEntityRepository.save(order);
        return order;
    }

    @Transactional
    public void payOrder(Long orderId) {
        // execute local method
        ExampleServiceAOrderEntity order = domainPayOrder(orderId);
        // call account service to deduct
        accountService.deduct(DeductDTO.builder()
                .opAmount(order.getOrderAmount().negate())
                .opId(orderId)
                .userId(order.getUserId())
                .build());
    }

    @NeedReconcile
    public ExampleServiceAOrderEntity domainPayOrder(Long orderId) {
        // update order status
        ExampleServiceAOrderEntity order = orderEntityRepository.findById(orderId).orElseThrow();
        order.setOrderStatus((byte) 2);
        order.setOrderRecAmount(BigDecimal.ZERO);
        return orderEntityRepository.save(order);
    }
}
