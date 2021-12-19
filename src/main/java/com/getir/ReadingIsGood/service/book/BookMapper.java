package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book mapDtoToBook(BookDto bookDto);
}
