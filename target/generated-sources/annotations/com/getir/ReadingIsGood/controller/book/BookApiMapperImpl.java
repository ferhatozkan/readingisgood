package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.controller.book.model.response.AddBookResponse;
import com.getir.ReadingIsGood.controller.book.model.response.AddBookResponse.AddBookResponseBuilder;
import com.getir.ReadingIsGood.controller.book.model.response.SetStockResponse;
import com.getir.ReadingIsGood.controller.book.model.response.SetStockResponse.SetStockResponseBuilder;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import com.getir.ReadingIsGood.service.book.model.BookDto.BookDtoBuilder;
import com.getir.ReadingIsGood.service.book.model.response.AddBookResponseDto;
import com.getir.ReadingIsGood.service.book.model.response.SetStockResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T23:10:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class BookApiMapperImpl implements BookApiMapper {

    @Override
    public BookDto mapAddBookRequestToBookDto(AddBookRequest request) {
        if ( request == null ) {
            return null;
        }

        BookDtoBuilder bookDto = BookDto.builder();

        bookDto.name( request.getName() );
        bookDto.stock( request.getStock() );
        bookDto.price( request.getPrice() );

        return bookDto.build();
    }

    @Override
    public AddBookResponse mapAddBookResponseDtoToAddBookResponse(AddBookResponseDto addBookResponse) {
        if ( addBookResponse == null ) {
            return null;
        }

        AddBookResponseBuilder addBookResponse1 = AddBookResponse.builder();

        addBookResponse1.id( addBookResponse.getId() );

        return addBookResponse1.build();
    }

    @Override
    public SetStockResponse mapSetStockResponseDtoToSetStockResponse(SetStockResponseDto setStockResponseDto) {
        if ( setStockResponseDto == null ) {
            return null;
        }

        SetStockResponseBuilder setStockResponse = SetStockResponse.builder();

        setStockResponse.id( setStockResponseDto.getId() );

        return setStockResponse.build();
    }
}
