package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.service.customer.CustomerService;
import com.getir.ReadingIsGood.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final CustomerApiMapper customerApiMapper;

    public CustomerController(CustomerService customerService,
                              OrderService orderService,
                              CustomerApiMapper customerApiMapper) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.customerApiMapper = customerApiMapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> addCustomer(@RequestBody AddCustomerRequest request){

        if(request.getAge() < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Age should be greater than or equal to 1");
        }

        if(request.getName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name cant be null");
        }

        int customerId = customerService.addCustomer(customerApiMapper.mapAddCustomerRequestToCustomerDto(request));


        return ResponseEntity.status(HttpStatus.OK).body(customerId);
    }

    @GetMapping(value = "/{customerId}/order")
    public ResponseEntity<Object> getOrders(@PathVariable int customerId, @RequestParam int page){

        if(customerId < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CustomerId should be greater than or equal to 1");
        }

        if(page < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Page should be greater than or equal to 0");
        }

        var orders = orderService.getOrders(customerId, page);

        var response = new ArrayList<OrderResponseViewModel>();

        for (var order : orders) {
            response.add(customerApiMapper.mapOrderResponseDtoToOrderResponseViewModel(order));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
