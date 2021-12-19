package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerApiMapper {

    @Mappings({ @Mapping(target = "name", source = "name"),
                @Mapping(target = "age", source = "age")})
    CustomerDto mapAddCustomerRequestToCustomerDto(AddCustomerRequest request);

    OrderResponseViewModel mapOrderResponseDtoToOrderResponseViewModel(OrderResponseDto orderResponseDto);
}
