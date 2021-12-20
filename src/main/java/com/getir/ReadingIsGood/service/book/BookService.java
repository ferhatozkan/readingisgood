package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.service.book.model.BookDto;
import com.getir.ReadingIsGood.service.book.model.response.AddBookResponseDto;
import com.getir.ReadingIsGood.service.book.model.response.SetStockResponseDto;

public interface BookService {

    AddBookResponseDto addBook(BookDto bookDto);

    SetStockResponseDto setStock(int bookId, int stockAmount);
}
