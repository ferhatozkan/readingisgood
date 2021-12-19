package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderApiMapper {
    AddOrderDto mapAddOrderRequestToAddOrderDto(AddOrderRequest request);
    OrderResponseViewModel mapOrderResponseDtoToOrderResponseViewModel(OrderResponseDto orderResponseDto);
}
