package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.AddOrderResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;

import java.util.ArrayList;
import java.util.Date;

public interface OrderService {

    OrderResponseDto getOrderById(int id);

    AddOrderResponseDto addOrder(AddOrderDto addOrderRequestDto);

    ArrayList<OrderResponseDto> getOrders(int customerId, int page);

    ArrayList<OrderResponseDto> getOrdersByDateInterval(Date startDate, Date endDate);
}


