package com.gsk.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {
    public PaymentServiceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}