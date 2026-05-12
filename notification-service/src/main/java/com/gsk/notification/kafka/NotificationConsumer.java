package com.gsk.notification.kafka;

import com.gsk.notification.entity.Notification;
import com.gsk.notification.kafka.order.OrderConfirmation;
import com.gsk.notification.kafka.payment.PaymentConfirmation;
import com.gsk.notification.model.NotificationType;
import com.gsk.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDateTime;

@Configuration
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;


    @KafkaListener(
            topics = "payment-topic",
            groupId = "paymentGroup")

    public void confirmPaymentSuccessConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("======Consume payment kafka in notification :: {} ", paymentConfirmation);
        log.info("saving payment Confirmation inside mongo");
        notificationRepository.save(Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build());
        log.info("saving payment Confirmation inside mongo");
        //todo send email
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "orderGroup"
    )
    public void confirmOrderSuccessConfirmation(OrderConfirmation orderConfirmation) {
        log.info("======Consume order kafka in notification :: {} ", orderConfirmation);
    }
}
