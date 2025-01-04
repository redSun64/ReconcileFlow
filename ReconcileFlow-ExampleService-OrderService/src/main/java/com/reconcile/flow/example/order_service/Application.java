package com.reconcile.flow.example.order_service;


import com.reconcile.flow.core.annotation.EnableReconcileFlow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableReconcileFlow
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
