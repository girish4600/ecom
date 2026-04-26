package org.gsk.order.orderline.model;

import lombok.*;

@Builder
@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@ToString
public class OrderEvent {
    private String orderId;
    private String email;
    private String status;
}