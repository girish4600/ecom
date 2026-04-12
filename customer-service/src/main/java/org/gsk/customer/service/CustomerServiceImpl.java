package org.gsk.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.gsk.customer.conversion.CustomerMapper;
import org.gsk.customer.handler.CustomerNotFoundException;
import org.gsk.customer.model.CustomerRequest;
import org.gsk.customer.model.CustomerResponse;
import org.gsk.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        return "Customer Created with Id :: " + repository.save(mapper.dtoToEntity(request)).getCustomerId();
    }

    @Override
    public CustomerResponse findById(Integer custId) {
        log.info("inside find method");
        var customer = repository.findById(custId).orElseThrow(() -> new CustomerNotFoundException(String.format("Customer Not Found Exception :: %d",custId)));
        return mapper.entityToDto(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}
