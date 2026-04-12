package org.gsk.customer.service;

import jakarta.validation.Valid;
import org.gsk.customer.model.CustomerRequest;
import org.gsk.customer.model.CustomerResponse;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CustomerService {
     String createCustomer(@Valid CustomerRequest request);

     CustomerResponse findById(Integer custId);

     List<CustomerResponse> findAll();
}

