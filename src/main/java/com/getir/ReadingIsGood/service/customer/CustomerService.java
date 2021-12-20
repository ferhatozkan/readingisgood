package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.customer.model.response.AddCustomerResponseDto;

public interface CustomerService {
    AddCustomerResponseDto addCustomer(CustomerDto customerDto);
}