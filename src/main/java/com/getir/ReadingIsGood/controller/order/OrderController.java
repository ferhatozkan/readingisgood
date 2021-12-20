package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.controller.order.model.request.GetOrdersByDateInterval;
import com.getir.ReadingIsGood.controller.order.model.response.AddOrderResponse;
import com.getir.ReadingIsGood.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<AddOrderResponse> addOrder(@Valid @RequestBody AddOrderRequest request){

        var addOrderResponseDto = orderService.addOrder(orderApiMapper.mapAddOrderRequestToAddOrderDto(request));
        return ResponseEntity.status(HttpStatus.OK).body(orderApiMapper.mapAddOrderResponseDtoToAddOrderResponse(addOrderResponseDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id){

        var orderResponseDto = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderApiMapper.mapOrderResponseDtoToOrderResponse(orderResponseDto));
    }

    @GetMapping(value = "/")
    public ResponseEntity<ArrayList<OrderResponse>> getOrdersByDateInterval(@Valid @RequestBody GetOrdersByDateInterval request){

        var orderResponseDtos = orderService.getOrdersByDateInterval(request.getStartDate(), request.getEndDate());
        var orders = new ArrayList<OrderResponse>();
        for (var orderResponseDto : orderResponseDtos) {
            orders.add(orderApiMapper.mapOrderResponseDtoToOrderResponse(orderResponseDto));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
