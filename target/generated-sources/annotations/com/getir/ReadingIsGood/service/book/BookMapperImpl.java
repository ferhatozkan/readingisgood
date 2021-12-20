package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.repository.book.Book.BookBuilder;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T23:10:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book mapDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        BookBuilder book = Book.builder();

        book.name( bookDto.getName() );
        book.stock( bookDto.getStock() );
        book.price( bookDto.getPrice() );

        return book.build();
    }
}
