package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.repository.customer.Customer;
import com.getir.ReadingIsGood.repository.customer.Customer.CustomerBuilder;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T03:03:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer mapDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.name( customerDto.getName() );
        customer.email( customerDto.getEmail() );
        customer.password( customerDto.getPassword() );
        customer.age( customerDto.getAge() );

        return customer.build();
    }
}
