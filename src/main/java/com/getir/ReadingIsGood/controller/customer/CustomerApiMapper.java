package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.AddCustomerResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.customer.model.response.AddCustomerResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerApiMapper {

    CustomerDto mapAddCustomerRequestToCustomerDto(AddCustomerRequest request);

    OrderResponse mapOrderResponseDtoToOrderResponse(OrderResponseDto orderResponseDto);

    AddCustomerResponse mapAddCustomerResponseDtoToAddCustomerResponse(AddCustomerResponseDto addCustomerResponseDto);
}
