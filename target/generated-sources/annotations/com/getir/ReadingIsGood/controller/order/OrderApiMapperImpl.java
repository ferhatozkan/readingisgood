package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.BookResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponse.BookResponseBuilder;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse.OrderResponseBuilder;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.controller.order.model.request.BookItem;
import com.getir.ReadingIsGood.controller.order.model.response.AddOrderResponse;
import com.getir.ReadingIsGood.controller.order.model.response.AddOrderResponse.AddOrderResponseBuilder;
import com.getir.ReadingIsGood.service.order.model.BookItemDto;
import com.getir.ReadingIsGood.service.order.model.BookItemDto.BookItemDtoBuilder;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto.AddOrderDtoBuilder;
import com.getir.ReadingIsGood.service.order.model.response.AddOrderResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T23:10:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class OrderApiMapperImpl implements OrderApiMapper {

    @Override
    public AddOrderDto mapAddOrderRequestToAddOrderDto(AddOrderRequest request) {
        if ( request == null ) {
            return null;
        }

        AddOrderDtoBuilder addOrderDto = AddOrderDto.builder();

        addOrderDto.customerId( request.getCustomerId() );
        addOrderDto.books( bookItemListToBookItemDtoArrayList( request.getBooks() ) );

        return addOrderDto.build();
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
    public AddOrderResponse mapAddOrderResponseDtoToAddOrderResponse(AddOrderResponseDto addOrderResponseDto) {
        if ( addOrderResponseDto == null ) {
            return null;
        }

        AddOrderResponseBuilder addOrderResponse = AddOrderResponse.builder();

        addOrderResponse.id( addOrderResponseDto.getId() );

        return addOrderResponse.build();
    }

    protected BookItemDto bookItemToBookItemDto(BookItem bookItem) {
        if ( bookItem == null ) {
            return null;
        }

        BookItemDtoBuilder bookItemDto = BookItemDto.builder();

        bookItemDto.bookId( bookItem.getBookId() );
        bookItemDto.count( bookItem.getCount() );

        return bookItemDto.build();
    }

    protected ArrayList<BookItemDto> bookItemListToBookItemDtoArrayList(List<BookItem> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<BookItemDto> arrayList = new ArrayList<BookItemDto>();
        for ( BookItem bookItem : list ) {
            arrayList.add( bookItemToBookItemDto( bookItem ) );
        }

        return arrayList;
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
