package com.getir.ReadingIsGood.controller.book;

import com.getir.ReadingIsGood.controller.book.model.request.AddBookRequest;
import com.getir.ReadingIsGood.controller.book.model.request.SetStockRequest;
import com.getir.ReadingIsGood.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> addBook(@RequestBody AddBookRequest request){

        if(request.getStock() < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock should be greater than or equal to 0");
        }

        if(request.getName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book name cant be null");
        }

        int bookId = bookService.addBook(bookApiMapper.mapAddBookRequestToBookDto(request));


        return ResponseEntity.status(HttpStatus.OK).body(bookId);
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<Object> setBookStock(@PathVariable int bookId, @RequestBody SetStockRequest request){

        if(bookId < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BookId should be greater than or equal to 1");
        }

        var stockAmount = request.getStock();
        if(stockAmount < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock should be greater than or equal to 0");
        }

        boolean isUpdated = bookService.setStock(bookId, stockAmount);

        if(!isUpdated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Stock update failed"); // Belki sadece loglanabilir ya da hem log hem hata donebiliriz
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
