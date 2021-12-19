package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookApiMapper {
    BookDto mapAddBookRequestToBookDto(AddBookRequest request);
}
