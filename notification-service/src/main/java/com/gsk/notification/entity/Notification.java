package com.gsk.notification.entity;


import com.gsk.notification.kafka.order.OrderConfirmation;
import com.gsk.notification.kafka.payment.PaymentConfirmation;
import com.gsk.notification.model.NotificationType;
import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
//@Document
public class Notification {

//    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
