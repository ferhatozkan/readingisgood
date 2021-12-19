package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.repository.customer.Customer;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer mapDtoToCustomer(CustomerDto customerDto);
}
