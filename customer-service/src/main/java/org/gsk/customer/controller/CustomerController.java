package org.gsk.customer.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.gsk.customer.model.CustomerRequest;
import org.gsk.customer.model.CustomerResponse;
import org.gsk.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerServiceImpl;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("inside controller createCustomer");
        return ResponseEntity.ok(customerServiceImpl.createCustomer(request));
    }

    @GetMapping("/{custId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable(name = "custId") Integer custId){
        log.info("inside controller findById");
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        log.info("Get All customers ");
        return new ResponseEntity<>(customerServiceImpl.findAll(), HttpStatus.FOUND);
    }
}
