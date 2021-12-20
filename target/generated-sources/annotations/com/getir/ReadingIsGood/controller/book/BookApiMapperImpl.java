package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import com.getir.ReadingIsGood.service.book.model.BookDto.BookDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T12:24:31+0300",
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
}
