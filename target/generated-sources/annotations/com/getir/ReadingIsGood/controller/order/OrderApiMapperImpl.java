package com.getir.ReadingIsGood.controller.order;

import com.getir.ReadingIsGood.controller.customer.model.response.BookResponseViewModel;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponseViewModel.BookResponseViewModelBuilder;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel.OrderResponseViewModelBuilder;
import com.getir.ReadingIsGood.controller.order.model.BookItemViewModel;
import com.getir.ReadingIsGood.controller.order.model.request.AddOrderRequest;
import com.getir.ReadingIsGood.service.order.model.BookItemDto;
import com.getir.ReadingIsGood.service.order.model.BookItemDto.BookItemDtoBuilder;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto.AddOrderDtoBuilder;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T12:24:31+0300",
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
        addOrderDto.books( bookItemViewModelArrayListToBookItemDtoArrayList( request.getBooks() ) );

        return addOrderDto.build();
    }

    @Override
    public OrderResponseViewModel mapOrderResponseDtoToOrderResponseViewModel(OrderResponseDto orderResponseDto) {
        if ( orderResponseDto == null ) {
            return null;
        }

        OrderResponseViewModelBuilder orderResponseViewModel = OrderResponseViewModel.builder();

        orderResponseViewModel.totalPrice( orderResponseDto.getTotalPrice() );
        orderResponseViewModel.books( bookResponseDtoArrayListToBookResponseViewModelArrayList( orderResponseDto.getBooks() ) );

        return orderResponseViewModel.build();
    }

    protected BookItemDto bookItemViewModelToBookItemDto(BookItemViewModel bookItemViewModel) {
        if ( bookItemViewModel == null ) {
            return null;
        }

        BookItemDtoBuilder bookItemDto = BookItemDto.builder();

        bookItemDto.bookId( bookItemViewModel.getBookId() );
        bookItemDto.count( bookItemViewModel.getCount() );

        return bookItemDto.build();
    }

    protected ArrayList<BookItemDto> bookItemViewModelArrayListToBookItemDtoArrayList(ArrayList<BookItemViewModel> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        ArrayList<BookItemDto> arrayList1 = new ArrayList<BookItemDto>();
        for ( BookItemViewModel bookItemViewModel : arrayList ) {
            arrayList1.add( bookItemViewModelToBookItemDto( bookItemViewModel ) );
        }

        return arrayList1;
    }

    protected BookResponseViewModel bookResponseDtoToBookResponseViewModel(BookResponseDto bookResponseDto) {
        if ( bookResponseDto == null ) {
            return null;
        }

        BookResponseViewModelBuilder bookResponseViewModel = BookResponseViewModel.builder();

        bookResponseViewModel.name( bookResponseDto.getName() );
        bookResponseViewModel.count( bookResponseDto.getCount() );
        bookResponseViewModel.price( bookResponseDto.getPrice() );

        return bookResponseViewModel.build();
    }

    protected ArrayList<BookResponseViewModel> bookResponseDtoArrayListToBookResponseViewModelArrayList(ArrayList<BookResponseDto> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        ArrayList<BookResponseViewModel> arrayList1 = new ArrayList<BookResponseViewModel>();
        for ( BookResponseDto bookResponseDto : arrayList ) {
            arrayList1.add( bookResponseDtoToBookResponseViewModel( bookResponseDto ) );
        }

        return arrayList1;
    }
}
