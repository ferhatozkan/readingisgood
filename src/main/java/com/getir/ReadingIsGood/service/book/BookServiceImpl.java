package com.getir.ReadingIsGood.service.book;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.service.book.model.BookDto;
import com.getir.ReadingIsGood.service.book.model.response.AddBookResponseDto;
import com.getir.ReadingIsGood.service.book.model.response.SetStockResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public AddBookResponseDto addBook(BookDto bookDto){
        int bookId = bookRepository.save(bookMapper.mapDtoToBook(bookDto)).getId();
        return AddBookResponseDto.builder().id(bookId).build();
    }

    @Override
    @Transactional
    public SetStockResponseDto setStock(int bookId, int stockAmount){

        var bookToUpdate = bookRepository.findById(bookId).orElse(null);

        if (bookToUpdate == null) throw new BusinessException("Book with given id do not exist");

        bookToUpdate.setStock(stockAmount);
        var id = bookRepository.save(bookToUpdate).getId();
        return SetStockResponseDto.builder().id(id).build();
    }
}
