package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.controller.order.model.response.AddOrderResponse;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.AddOrderResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderApiMapper {
    AddOrderDto mapAddOrderRequestToAddOrderDto(AddOrderRequest request);
    OrderResponse mapOrderResponseDtoToOrderResponse(OrderResponseDto orderResponseDto);
    AddOrderResponse mapAddOrderResponseDtoToAddOrderResponse(AddOrderResponseDto addOrderResponseDto);
}
