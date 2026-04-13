package org.gsk.order.outer_api;


import org.gsk.order.model.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@FeignClient(name = "customer-service"/*, url = "http://localhost:9091"*/)
public interface CustomerService {

     @GetMapping("/v1/customer/{custId}")
     Optional<CustomerResponse> findById(@PathVariable(name = "custId") Integer custId);

}

