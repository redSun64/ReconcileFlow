package com.reconcile.flow.scheduler;

import com.reconcile.flow.core.annotation.EnableReconcileFlow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableReconcileFlow
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}