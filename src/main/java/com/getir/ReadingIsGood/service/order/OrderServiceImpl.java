package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.repository.order.Order;
import com.getir.ReadingIsGood.repository.order.OrderRepository;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBook;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBooksRepository;
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
    public int addOrder(AddOrderDto addOrderRequestDto) {

        if (addOrderRequestDto.getCustomerId() < 1)
            // Log
            return 0;

        if (addOrderRequestDto.getBooks() == null) {
            // Log
            return 0;
        }

        if (addOrderRequestDto.getBooks().stream().anyMatch(n -> n.getBookId() < 1)) {
            // Log
            return 0;
        }

        if (addOrderRequestDto.getBooks().stream().anyMatch(n -> n.getCount() < 1)) {
            // Log
            return 0;
        }

        var requestedBooks = addOrderRequestDto.getBooks();

        var requestedBookIds = requestedBooks
                .stream()
                .map(x -> x.getBookId())
                .collect(Collectors.toList());


        for (var bookId : requestedBookIds) {
            // Check if all books exist
            if (!bookRepository.existsById(bookId)) {
                return 0;
            }
        }

        List<Book> books = bookRepository.findAllById(requestedBookIds);

        double totalPrice = 0;

        for (var requestedBook : requestedBooks) {
            var book = books.stream()
                    .filter(n -> n.getId() == requestedBook.getBookId())
                    .findFirst()
                    .orElse(null);

            if (book == null)
                return 0;

            //Check Stock
            if (book.getStock() < requestedBook.getCount())
                return 0;

            book.setStock(book.getStock() - requestedBook.getCount());

            var bookStock = bookRepository.save(book).getStock();

            if (book.getStock() != bookStock)
                return 0;

            var bookPrice = book.getPrice();
            totalPrice += bookPrice * requestedBook.getCount();
        }

        Order newOrder = Order.builder()
                .customerId(addOrderRequestDto.getCustomerId())
                .totalPrice(totalPrice)
                .build();

        int orderId = orderRepository.save(newOrder).getId();

        if (orderId == 0)
            return 0;

        ArrayList<OrderBook> orderBookArrayList = new ArrayList<OrderBook>();

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
            return 0;
        }

        return orderId;
    }

    @Override
    public ArrayList<OrderResponseDto> getOrders(int customerId, int page) {

        Pageable pageable = PageRequest.of(page, 3, Sort.by("CreatedOn").descending());
        var orders = orderRepository.findAllByCustomerId(customerId, pageable);

        var response = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            response.add(orderResponseDto);
        }

        return response;
    }

    @Override
    public ArrayList<OrderResponseDto> getOrdersByDateInterval(Date startDate, Date endDate) {

        var orders = orderRepository.findByCreatedOnBetween(startDate, endDate);

        var response = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            response.add(orderResponseDto);
        }

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