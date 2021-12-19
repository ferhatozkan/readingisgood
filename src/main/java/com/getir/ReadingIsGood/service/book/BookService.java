package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.service.book.model.BookDto;

public interface BookService {

    int addBook(BookDto bookDto);

    boolean setStock(int bookId, int stockAmount);
}
