package com.reconcile.flow.example.order_service.controller;


import com.reconcile.flow.example.order_service.entity.ExampleServiceAOrderEntity;
import com.reconcile.flow.example.order_service.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ExampleServiceAOrderEntity addOrder(@RequestBody ExampleServiceAOrderEntity order) {
        return orderService.addOrder(order);
}

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public void payOrder(@RequestBody ExampleServiceAOrderEntity order) {
        orderService.payOrder(order.getId());
    }
}
