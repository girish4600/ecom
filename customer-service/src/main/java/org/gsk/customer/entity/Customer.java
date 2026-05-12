package org.gsk.customer.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@Builder
@Entity
@Table(name = "CUSTOMER_TBL")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
