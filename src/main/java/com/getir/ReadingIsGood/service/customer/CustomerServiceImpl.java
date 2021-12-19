package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.repository.customer.CustomerRepository;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper customerMapper) {
        this.repository = repository;
        this.customerMapper = customerMapper;
    }

    @Override
    public int addCustomer(CustomerDto customerDto) {
        int customerId = repository.save(customerMapper.mapDtoToCustomer(customerDto)).getId();
        return customerId;
    }
}
