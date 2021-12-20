package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.controller.order.model.request.GetOrdersByDateInterval;
import com.getir.ReadingIsGood.service.GenericResponse;
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
    public GenericResponse<Integer> addOrder(@RequestBody AddOrderRequest request){

        var response = new GenericResponse<Integer>();
        response.setSuccess(true);

        var result = orderService.addOrder(orderApiMapper.mapAddOrderRequestToAddOrderDto(request));

        if(!result.isSuccess()){
            response.setSuccess(false);
            response.setMessage(result.getMessage());
            return response;
        }

        response.setData(result.getData());
        return response;
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
    public GenericResponse<ArrayList<OrderResponseViewModel>> getOrdersByDateInterval(@RequestBody GetOrdersByDateInterval request){

        var result = orderService.getOrdersByDateInterval(request.getStartDate(), request.getEndDate());

        var response = new GenericResponse<ArrayList<OrderResponseViewModel>>();
        response.setSuccess(true);

        if(!result.isSuccess()){
            response.setSuccess(false);
            response.setMessage(result.getMessage());
            return response;
        }

        var orders = new ArrayList<OrderResponseViewModel>();

        for (var order : result.getData()) {
            orders.add(orderApiMapper.mapOrderResponseDtoToOrderResponseViewModel(order));
        }

        response.setData(orders);
        return response;
    }
}
