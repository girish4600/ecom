package com.gsk.payment.kafka;

import com.gsk.payment.kafka.model.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    @Autowired
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest request) {
        Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request)
                .setHeader(KafkaHeaders.TOPIC,"payment-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
