package com.reconcile.flow.example.order_service.service;

import cn.hutool.core.bean.BeanUtil;
import com.reconcile.flow.core.annotation.NeedReconcile;
import com.reconcile.flow.core.annotation.ReconcileFlowService;
import com.reconcile.flow.example.order_service.dto.DeductDTO;
import com.reconcile.flow.example.order_service.dto.OrderDTO;
import com.reconcile.flow.example.order_service.entity.ExampleServiceAOrderEntity;
import com.reconcile.flow.example.order_service.infrastruction.AccountServiceClient;
import com.reconcile.flow.example.order_service.repository.ExampleServiceAOrderEntityRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.reconcile.flow.example.order_service.constants.OrderConstants.ORDER_PAY_SUCCESS;
import static com.reconcile.flow.example.order_service.constants.OrderConstants.WAIT_PAY;

@Service
@ReconcileFlowService
@Slf4j
public class OrderService {

    @Resource
    private ExampleServiceAOrderEntityRepository orderEntityRepository;

    @Resource
    private AccountServiceClient accountService;

    @Transactional
    public ExampleServiceAOrderEntity addOrder(ExampleServiceAOrderEntity order) {
        order.setOrderStatus(WAIT_PAY);
        order = orderEntityRepository.save(order);
        return order;
    }

    @Transactional
    @NeedReconcile("domainPayOrder")
    public void payOrder(Long orderId) {
        // execute local method
        OrderDTO order = domainPayOrder(orderId);
        // call account service to deduct
        accountService.deduct(DeductDTO.builder()
                .opAmount(order.getOrderAmount().negate())
                .opId(orderId)
                .userId(order.getUserId())
                .build());
        Integer a = 1, b = 0;
        log.info("payOrder: " + a / b);
    }


//    public Long domainPayOrder(Long orderId) {
//        // update order status
//        ExampleServiceAOrderEntity order = orderEntityRepository.findById(orderId).orElseThrow();
//        order.setOrderStatus((byte) 2);
//        order.setOrderRecAmount(BigDecimal.ZERO);
//        orderEntityRepository.save(order);
//        return order.getId();
//    }

    public OrderDTO domainPayOrder(Long orderId) {
        // update order status
        ExampleServiceAOrderEntity order = orderEntityRepository.findById(orderId).orElseThrow();
        order.setOrderStatus(ORDER_PAY_SUCCESS);
        order.setOrderRecAmount(BigDecimal.ZERO);
        ExampleServiceAOrderEntity exampleServiceAOrderEntity = orderEntityRepository.save(order);
        return BeanUtil.copyProperties(exampleServiceAOrderEntity, OrderDTO.class);
    }
}
