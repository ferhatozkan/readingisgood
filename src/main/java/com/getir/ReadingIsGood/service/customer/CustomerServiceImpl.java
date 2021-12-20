package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import com.getir.ReadingIsGood.repository.customer.CustomerRepository;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.customer.model.response.AddCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public AddCustomerResponseDto addCustomer(CustomerDto customerDto){

        var isExist = customerRepository.existsByEmail(customerDto.getEmail());

        if (isExist) throw new BusinessException("Customer with this email already exists");

        int customerId = customerRepository.save(customerMapper.mapDtoToCustomer(customerDto)).getId();

        return AddCustomerResponseDto.builder().id(customerId).build();
    }
}
