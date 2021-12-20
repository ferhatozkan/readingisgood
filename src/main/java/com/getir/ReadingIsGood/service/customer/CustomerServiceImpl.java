package com.getir.ReadingIsGood.service.customer;

import com.getir.ReadingIsGood.repository.customer.CustomerRepository;
import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public GenericResponse<Integer> addCustomer(CustomerDto customerDto) {

        var response = new GenericResponse<Integer>();
        response.setSuccess(true);

        if(customerDto.getAge() < 1){
            response.setMessage("Age must be greater than or equal to 1");
            response.setSuccess(false);
            return response;
        }

        if(customerDto.getName() == null){
            response.setMessage("Name can not be null");
            response.setSuccess(false);
            return response;
        }

        if(customerDto.getEmail() == null){
            response.setMessage("Email can not be null");
            response.setSuccess(false);
            return response;
        }

        if(customerDto.getPassword() == null){
            response.setMessage("Password can not be null");
            response.setSuccess(false);
            return response;
        }

        var isExist = customerRepository.existsByEmail(customerDto.getEmail());

        if(isExist){
            response.setMessage("Customer with this email already exists");
            response.setSuccess(false);
            return response;
        }

        int customerId = customerRepository.save(customerMapper.mapDtoToCustomer(customerDto)).getId();
        response.setData(customerId);

        return response;
    }
}
