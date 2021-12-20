package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.repository.order.Order;
import com.getir.ReadingIsGood.repository.order.OrderRepository;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBook;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBooksRepository;
import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
    private final OrderBooksRepository orderBooksRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, BookRepository bookRepository, OrderBooksRepository orderBooksRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.bookRepository = bookRepository;
        this.orderBooksRepository = orderBooksRepository;
    }

    @Override
    public OrderResponseDto getOrderById(int id) {

        var order = orderRepository.findById(id).orElse(null);
        if(order == null)
            return null;

        var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
        return orderResponseDto;
    }

    @Override
    public GenericResponse<Integer> addOrder(AddOrderDto addOrderRequestDto) {

        var response = new GenericResponse<Integer>();
        response.setSuccess(true);

        if(addOrderRequestDto == null){
            response.setMessage("Please provide a customerId");
            response.setSuccess(false);
            return response;
        }

        if(addOrderRequestDto.getCustomerId() < 1){
            response.setMessage("CustomerId must be greater than or equal to 1");
            response.setSuccess(false);
            return response;
        }

        if(addOrderRequestDto.getBooks() == null){
            response.setMessage("Please provide a books to order");
            response.setSuccess(false);
            return response;
        }

        if(addOrderRequestDto.getBooks().stream().anyMatch(n -> n.getBookId() < 1)){
            response.setMessage("BookId must be greater than or equal to 1");
            response.setSuccess(false);
            return response;
        }

        if (addOrderRequestDto.getBooks().stream().anyMatch(n -> n.getCount() < 1)) {
            response.setMessage("Added count should be greater than or equal to 1");
            response.setSuccess(false);
            return response;
        }

        var requestedBooks = addOrderRequestDto.getBooks();

        var requestedBookIds = requestedBooks
                .stream()
                .map(x -> x.getBookId())
                .collect(Collectors.toList());


        for (var bookId : requestedBookIds) {
            if (!bookRepository.existsById(bookId)) {
                response.setMessage("Added book does not exist");
                response.setSuccess(false);
                return response;
            }
        }

        List<Book> books = bookRepository.findAllById(requestedBookIds);

        double totalPrice = 0;

        for (var requestedBook : requestedBooks) {
            var book = books.stream()
                    .filter(n -> n.getId() == requestedBook.getBookId())
                    .findFirst()
                    .orElse(null);

            if (book == null) {
                response.setMessage("Added book does not exist");
                response.setSuccess(false);
                return response;
            }

            if (book.getStock() < requestedBook.getCount()) {
                response.setMessage(String.format("Stock of %s book is not enough", book.getName()));
                response.setSuccess(false);
                return response;
            }

            book.setStock(book.getStock() - requestedBook.getCount());

            var bookStock = bookRepository.save(book).getStock();

            if (book.getStock() != bookStock) {
                response.setMessage("Stock update operation failed!");
                response.setSuccess(false);
                return response;
            }

            var bookPrice = book.getPrice();

            totalPrice += bookPrice * requestedBook.getCount();
        }

        Order newOrder = Order.builder()
                .customerId(addOrderRequestDto.getCustomerId())
                .totalPrice(totalPrice)
                .build();

        int orderId = orderRepository.save(newOrder).getId();

        if (orderId == 0){
            response.setMessage("Order creation failed!");
            response.setSuccess(false);
            return response;
        }

        ArrayList<OrderBook> orderBookArrayList = new ArrayList<>();

        for (var requestedBook : requestedBooks) {
            var book = books.stream()
                    .filter(n -> n.getId() == requestedBook.getBookId())
                    .findFirst()
                    .orElse(null);

            OrderBook newOrderBook = OrderBook.builder()
                    .orderId(orderId)
                    .bookId(requestedBook.getBookId())
                    .count(requestedBook.getCount())
                    .price(book.getPrice())
                    .build();

            orderBookArrayList.add(newOrderBook);
        }

        List<OrderBook> orderBooks = orderBooksRepository.saveAll(orderBookArrayList);

        if (orderBooks.stream().count() != requestedBooks.stream().count()) {
            response.setMessage("OrderStock insertion failed!");
            response.setSuccess(false);
            return response;
        }

        response.setData(orderId);
        return response;
    }

    @Override
    public GenericResponse<ArrayList<OrderResponseDto>>  getOrders(int customerId, int page) {

        var response = new GenericResponse<ArrayList<OrderResponseDto>>();
        response.setSuccess(true);

        if(customerId < 1){
            response.setSuccess(false);
            response.setMessage("CustomerId should be greater than or equal to 1");
            return response;
        }

        if(page < 0){
            response.setSuccess(false);
            response.setMessage("Page should be greater than or equal to 0");
            return response;
        }

        Pageable pageable = PageRequest.of(page, 3, Sort.by("CreatedOn").descending());
        var orders = orderRepository.findAllByCustomerId(customerId, pageable);

        var result = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            result.add(orderResponseDto);
        }

        response.setData(result);
        return response;
    }

    @Override
    public GenericResponse<ArrayList<OrderResponseDto>> getOrdersByDateInterval(Date startDate, Date endDate) {

        var response = new GenericResponse<ArrayList<OrderResponseDto>>();
        response.setSuccess(true);

        if(startDate == null || endDate == null || endDate.before(startDate)){
            response.setSuccess(false);
            response.setMessage("Page should be greater than or equal to 0");
            return response;
        }

        var orders = orderRepository.findByCreatedOnBetween(startDate, endDate);

        var result = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            result.add(orderResponseDto);
        }

        response.setData(result);
        return response;
    }

    private OrderResponseDto getOrderResponseDto(int id, double totalPrice) {

        var orderBooks = orderBooksRepository.findAllByOrderId(id);
        var bookIds = orderBooks
                .stream()
                .map(x -> x.getBookId())
                .collect(Collectors.toList());

        var books = bookRepository.findAllById(bookIds);

        var bookList = new ArrayList<BookResponseDto>();
        for (var book : books) {
            var orderBook = orderBooks.stream()
                    .filter(n -> n.getBookId() == book.getId())
                    .findFirst()
                    .orElse(null);

            bookList.add(BookResponseDto.builder().name(book.getName()).count(orderBook.getCount()).price(orderBook.getPrice()).build());
        }

        var orderResponse = OrderResponseDto.builder().books(bookList).totalPrice(totalPrice).build();
        return orderResponse;
    }
}
