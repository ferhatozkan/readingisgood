package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponseViewModel;
import com.getir.ReadingIsGood.controller.customer.model.response.BookResponseViewModel.BookResponseViewModelBuilder;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel.OrderResponseViewModelBuilder;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto;
import com.getir.ReadingIsGood.service.customer.model.CustomerDto.CustomerDtoBuilder;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T03:03:36+0300",
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
    public OrderResponseViewModel mapOrderResponseDtoToOrderResponseViewModel(OrderResponseDto orderResponseDto) {
        if ( orderResponseDto == null ) {
            return null;
        }

        OrderResponseViewModelBuilder orderResponseViewModel = OrderResponseViewModel.builder();

        orderResponseViewModel.totalPrice( orderResponseDto.getTotalPrice() );
        orderResponseViewModel.books( bookResponseDtoArrayListToBookResponseViewModelArrayList( orderResponseDto.getBooks() ) );

        return orderResponseViewModel.build();
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
