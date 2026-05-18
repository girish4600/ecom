package com.gsk.notification.kafka;

import com.gsk.notification.entity.Notification;
import com.gsk.notification.kafka.order.OrderConfirmation;
import com.gsk.notification.kafka.payment.PaymentConfirmation;
import com.gsk.notification.model.NotificationType;
import com.gsk.notification.repository.NotificationRepository;
import com.gsk.notification.service.EmailService;
import jakarta.mail.MessagingException;
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

    private final EmailService emailService;


    @KafkaListener(
            topics = "payment-topic",
            groupId = "paymentGroup")

    public void confirmPaymentSuccessConfirmation(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("======Consume payment kafka in notification :: {} ", paymentConfirmation);
        log.info("saving payment Confirmation inside mongo");
        var notification = notificationRepository.save(Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build());
        System.out.println("Notification :: " + notification);
        log.info("saving payment Confirmation inside mongo");
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(), paymentConfirmation.customerFirstName()+ " " + paymentConfirmation.customerLastName(), paymentConfirmation.amount(), paymentConfirmation.orderReference());
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "orderGroup"
    )
    public void confirmOrderSuccessConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("======Consume order kafka in notification :: {} ", orderConfirmation);
        // to check this need to open http://localhost:8025/
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        log.info("INFO - order saved to mongodb");
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),
                orderConfirmation.customer().firstName()+ " " + orderConfirmation.customer().lastName(), orderConfirmation.amount(), orderConfirmation.reference(), orderConfirmation.products());
    }
}
