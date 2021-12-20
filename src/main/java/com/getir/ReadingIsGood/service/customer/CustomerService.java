package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;

public interface CustomerService {

    GenericResponse<Integer> addCustomer(CustomerDto customerDto);
}


