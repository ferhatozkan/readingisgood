package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.AddCustomerResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.AddCustomerResponse.AddCustomerResponseBuilder;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponse.BookResponseBuilder;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse.OrderResponseBuilder;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto.CustomerDtoBuilder;
import com.getir.ReadingIsGood.service.customer.model.response.AddCustomerResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T23:10:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CustomerApiMapperImpl implements CustomerApiMapper {

    @Override
    public CustomerDto mapAddCustomerRequestToCustomerDto(AddCustomerRequest request) {
        if ( request == null ) {
            return null;
        }

        CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.name( request.getName() );
        customerDto.email( request.getEmail() );
        customerDto.password( request.getPassword() );
        customerDto.age( request.getAge() );

        return customerDto.build();
    }

    @Override
    public OrderResponse mapOrderResponseDtoToOrderResponse(OrderResponseDto orderResponseDto) {
        if ( orderResponseDto == null ) {
            return null;
        }

        OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.totalPrice( orderResponseDto.getTotalPrice() );
        orderResponse.books( bookResponseDtoArrayListToBookResponseArrayList( orderResponseDto.getBooks() ) );

        return orderResponse.build();
    }

    @Override
    public AddCustomerResponse mapAddCustomerResponseDtoToAddCustomerResponse(AddCustomerResponseDto addCustomerResponseDto) {
        if ( addCustomerResponseDto == null ) {
            return null;
        }

        AddCustomerResponseBuilder addCustomerResponse = AddCustomerResponse.builder();

        addCustomerResponse.id( addCustomerResponseDto.getId() );

        return addCustomerResponse.build();
    }

    protected BookResponse bookResponseDtoToBookResponse(BookResponseDto bookResponseDto) {
        if ( bookResponseDto == null ) {
            return null;
        }

        BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.name( bookResponseDto.getName() );
        bookResponse.count( bookResponseDto.getCount() );
        bookResponse.price( bookResponseDto.getPrice() );

        return bookResponse.build();
    }

    protected ArrayList<BookResponse> bookResponseDtoArrayListToBookResponseArrayList(ArrayList<BookResponseDto> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        ArrayList<BookResponse> arrayList1 = new ArrayList<BookResponse>();
        for ( BookResponseDto bookResponseDto : arrayList ) {
            arrayList1.add( bookResponseDtoToBookResponse( bookResponseDto ) );
        }

        return arrayList1;
    }
}
