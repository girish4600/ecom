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

   /* @Autowired
    private NotificationRepository notificationRepository;*/

    @KafkaListener(topics = "payment-topic")
    public void confirmPaymentSuccessConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("======Consume payment kafka in notification :: {} ", paymentConfirmation);
        /*notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );*/
    }

    @KafkaListener(topics = "order-topic")
    public void confirmOrderSuccessConfirmation(OrderConfirmation orderConfirmation) {
        log.info("======Consume order kafka in notification :: {} ", orderConfirmation);
        /*notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );*/
    }

}
