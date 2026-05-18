package org.gsk.customer.conversion;

import org.gsk.customer.entity.Customer;
import org.gsk.customer.model.CustomerRequest;
import org.gsk.customer.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer dtoToEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse entityToDto(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
