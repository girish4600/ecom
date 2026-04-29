package org.gsk.order.kafka;

import lombok.RequiredArgsConstructor;
import org.gsk.order.kafka.model.OrderNotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
@RequiredArgsConstructor
public class NotificationProducer {

    @Autowired
    private final KafkaTemplate<String, OrderNotificationRequest> kafkaTemplate;

    public void sendNotification(OrderNotificationRequest request) {
        Message<OrderNotificationRequest> message = MessageBuilder.withPayload(request)
                .setHeader(KafkaHeaders.TOPIC,"order-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
