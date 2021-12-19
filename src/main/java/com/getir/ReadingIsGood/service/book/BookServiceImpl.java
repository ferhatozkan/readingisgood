package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(BookDto bookDto) {
        int bookId = bookRepository.save(bookMapper.mapDtoToBook(bookDto)).getId();
        return bookId;
    }

    @Override
    public boolean setStock(int bookId, int stockAmount) {

        try {
            Book bookToUpdate = bookRepository.getById(bookId);
            bookToUpdate.setStock(stockAmount);
            var id = bookRepository.save(bookToUpdate).getId();
            return id != null;
        }
        catch(Exception ex){
            return false;
        }
    }
}
