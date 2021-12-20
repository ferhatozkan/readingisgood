package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.controller.book.model.request.SetStockRequest;
import com.getir.ReadingIsGood.controller.book.model.response.AddBookResponse;
import com.getir.ReadingIsGood.controller.book.model.response.SetStockResponse;
import com.getir.ReadingIsGood.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;
    private final BookApiMapper bookApiMapper;

    public BookController(BookService bookService, BookApiMapper bookApiMapper) {
        this.bookService = bookService;
        this.bookApiMapper = bookApiMapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<AddBookResponse> addBook(@Valid @RequestBody AddBookRequest request){

        var addBookResponseDto = bookService.addBook(bookApiMapper.mapAddBookRequestToBookDto(request));
        return ResponseEntity.status(HttpStatus.OK).body(bookApiMapper.mapAddBookResponseDtoToAddBookResponse(addBookResponseDto));
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<SetStockResponse> setBookStock(@PathVariable int bookId, @Valid @RequestBody SetStockRequest request){

        var setStockResponseDto = bookService.setStock(bookId, request.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(bookApiMapper.mapSetStockResponseDtoToSetStockResponse(setStockResponseDto));
    }
}
