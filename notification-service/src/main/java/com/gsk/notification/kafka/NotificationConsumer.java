package com.gsk.notification.kafka;

import com.gsk.notification.kafka.order.OrderConfirmation;
import com.gsk.notification.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    @KafkaListener(
            topics = "payment-topic",
            groupId = "paymentGroup")
    public void confirmPaymentSuccessConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("======Consume payment kafka in notification :: {} ", paymentConfirmation);
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "orderGroup"
    )
    public void confirmOrderSuccessConfirmation(OrderConfirmation orderConfirmation) {
        log.info("======Consume order kafka in notification :: {} ", orderConfirmation);
    }
}
