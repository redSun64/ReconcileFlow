package com.reconcile.flow.example.account_service;


import com.reconcile.flow.core.annotation.EnableReconcileFlow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableReconcileFlow
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
