package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;

import java.util.ArrayList;
import java.util.Date;

public interface OrderService {

    OrderResponseDto getOrderById(int id);

    int addOrder(AddOrderDto addOrderDto);

    ArrayList<OrderResponseDto> getOrders(int customerId, int page);

    ArrayList<OrderResponseDto> getOrdersByDateInterval(Date startDate, Date endDate);
}


