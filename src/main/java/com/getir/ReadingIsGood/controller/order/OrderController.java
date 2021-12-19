package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.controller.order.model.request.GetOrdersByDateInterval;
import com.getir.ReadingIsGood.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderApiMapper orderApiMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderApiMapper orderApiMapper) {
        this.orderService = orderService;
        this.orderApiMapper = orderApiMapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> addOrder(@RequestBody AddOrderRequest request){

        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request can't be null");
        }

        if(request.getCustomerId() < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CustomerId should be greater or equal to 1");
        }

        if(request.getBooks() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("At least one book should be in the order");
        }

        if(request.getBooks().stream().anyMatch(n -> n.getBookId() < 1)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book id should be greater than or equal to 1");
        }

        if(request.getBooks().stream().anyMatch(n -> n.getCount() < 1)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Added count should be greater than or equal to 1");
        }

        int orderId = orderService.addOrder(orderApiMapper.mapAddOrderRequestToAddOrderDto(request));

        if (orderId == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while inserting order");
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable int id){

        if(id < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OrderId should be greater than or equal to 1");
        }

        var order = orderService.getOrderById(id);

        if(order == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order Not found");

        return ResponseEntity.status(HttpStatus.OK).body(orderApiMapper.mapOrderResponseDtoToOrderResponseViewModel(order));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getOrdersByDateInterval(@RequestBody GetOrdersByDateInterval request){

        if((request.getEndDate() == null && request.getStartDate() == null) || request.getEndDate().before(request.getStartDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OrderId should be greater than or equal to 1");
        }

        var orders = orderService.getOrdersByDateInterval(request.getStartDate(), request.getEndDate());

        var response = new ArrayList<OrderResponseViewModel>();

        for (var order : orders) {
            response.add(orderApiMapper.mapOrderResponseDtoToOrderResponseViewModel(order));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
