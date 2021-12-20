package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.controller.book.model.response.AddBookResponse;
import com.getir.ReadingIsGood.controller.book.model.response.SetStockResponse;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import com.getir.ReadingIsGood.service.book.model.response.AddBookResponseDto;
import com.getir.ReadingIsGood.service.book.model.response.SetStockResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookApiMapper {
    BookDto mapAddBookRequestToBookDto(AddBookRequest request);
    AddBookResponse mapAddBookResponseDtoToAddBookResponse(AddBookResponseDto addBookResponse);
    SetStockResponse mapSetStockResponseDtoToSetStockResponse(SetStockResponseDto setStockResponseDto);
}
