package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.repository.order.Order;
import com.getir.ReadingIsGood.repository.order.OrderRepository;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBook;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBooksRepository;
import com.getir.ReadingIsGood.service.order.model.request.AddOrderDto;
import com.getir.ReadingIsGood.service.order.model.response.AddOrderResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public OrderResponseDto getOrderById(int id){

        if (id < 1) throw new BusinessException("OrderId should be greater than or equal to 1");

        var order = orderRepository.findById(id).orElse(null);

        if (order == null) throw new BusinessException("Order not found");

        var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());

        return orderResponseDto;
    }

    @Override
    @Transactional
    public AddOrderResponseDto addOrder(AddOrderDto addOrderRequestDto){

        var requestedBooks = addOrderRequestDto.getBooks();

        var requestedBookIds = requestedBooks.stream().map(x -> x.getBookId()).collect(Collectors.toList());

        for (var bookId : requestedBookIds) {
            if (!bookRepository.existsById(bookId)) throw new BusinessException("Added book does not exist");
        }

        List<Book> books = bookRepository.findAllById(requestedBookIds);

        double totalPrice = 0;

        for (var requestedBook : requestedBooks) {
            var book = books.stream().filter(n -> n.getId() == requestedBook.getBookId()).findFirst().orElse(null);

            if (book == null) throw new BusinessException("Added book does not exist");

            if (book.getStock() < requestedBook.getCount())
                throw new BusinessException(String.format("Stock of %s book is not enough", book.getName()));

            book.setStock(book.getStock() - requestedBook.getCount());

            bookRepository.save(book).getStock();

            var bookPrice = book.getPrice();

            totalPrice += bookPrice * requestedBook.getCount();
        }

        Order newOrder = Order.builder().customerId(addOrderRequestDto.getCustomerId()).totalPrice(totalPrice).build();

        int orderId = orderRepository.save(newOrder).getId();

        ArrayList<OrderBook> orderBookArrayList = new ArrayList<>();

        for (var requestedBook : requestedBooks) {
            var book = books.stream().filter(n -> n.getId() == requestedBook.getBookId()).findFirst().orElse(null);

            OrderBook newOrderBook = OrderBook.builder().orderId(orderId).bookId(requestedBook.getBookId()).count(requestedBook.getCount()).price(book.getPrice()).build();

            orderBookArrayList.add(newOrderBook);
        }

        orderBooksRepository.saveAll(orderBookArrayList);

        return AddOrderResponseDto.builder().id(orderId).build();
    }

    @Override
    @Transactional
    public ArrayList<OrderResponseDto> getOrders(int customerId, int page){

        if (customerId < 1) throw new BusinessException("customerId must be greater than or equal to 1");

        if (page < 0) throw new BusinessException("page must be greater than or equal to 0");

        Pageable pageable = PageRequest.of(page, 3, Sort.by("CreatedOn").descending());

        var orders = orderRepository.findAllByCustomerId(customerId, pageable);

        var result = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            result.add(orderResponseDto);
        }

        return result;
    }

    @Override
    @Transactional
    public ArrayList<OrderResponseDto> getOrdersByDateInterval(Date startDate, Date endDate){

        if (endDate.before(startDate)) throw new BusinessException("endDate can not be before StartDate");

        var orders = orderRepository.findByCreatedOnBetween(startDate, endDate);

        var result = new ArrayList<OrderResponseDto>();

        for (var order : orders) {
            var orderResponseDto = getOrderResponseDto(order.getId(), order.getTotalPrice());
            result.add(orderResponseDto);
        }

        return result;
    }

    @Transactional
    private OrderResponseDto getOrderResponseDto(int id, double totalPrice){

        var orderBooks = orderBooksRepository.findAllByOrderId(id);

        if (orderBooks.isEmpty()) throw new BusinessException("book in order not found!");

        var bookIds = orderBooks.stream().map(x -> x.getBookId()).collect(Collectors.toList());

        var books = bookRepository.findAllById(bookIds);

        if (books.isEmpty()) throw new BusinessException("order with no books cant exist!");

        var bookList = new ArrayList<BookResponseDto>();
        for (var book : books) {
            var orderBook = orderBooks.stream().filter(n -> n.getBookId() == book.getId()).findFirst().orElse(null);

            bookList.add(BookResponseDto.builder().name(book.getName()).count(orderBook.getCount()).price(orderBook.getPrice()).build());
        }

        var orderResponse = OrderResponseDto.builder().books(bookList).totalPrice(totalPrice).build();
        return orderResponse;
    }
}
