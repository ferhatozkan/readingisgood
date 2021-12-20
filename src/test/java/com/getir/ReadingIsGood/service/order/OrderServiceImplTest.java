package com.getir.ReadingIsGood.service.order;

import com.getir.ReadingIsGood.repository.book.Book;
import com.getir.ReadingIsGood.repository.book.BookRepository;
import com.getir.ReadingIsGood.repository.order.Order;
import com.getir.ReadingIsGood.repository.order.OrderRepository;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBook;
import com.getir.ReadingIsGood.repository.orderBooks.OrderBooksRepository;
import com.getir.ReadingIsGood.service.order.model.response.BookResponseDto;
import com.getir.ReadingIsGood.service.order.model.response.OrderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private OrderBooksRepository orderBooksRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, orderMapper, bookRepository, orderBooksRepository);
    }

    @Test
    void getOrderById_whenOrderFound_thenShouldReturnSuccessfully() throws Exception {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(Order.builder().id(2).totalPrice(80).build()));
        when(orderBooksRepository.findAllByOrderId(2)).thenReturn(new ArrayList<>(Arrays.asList(OrderBook.builder().bookId(1).count(2).price(10).build(), OrderBook.builder().bookId(2).price(20).count(3).build())));
        when(bookRepository.findAllById(Arrays.asList(1,2))).thenReturn(new ArrayList<>(Arrays.asList(Book.builder().id(1).name("test book 1").build(), Book.builder().id(2).name("test book 2").build())));
        OrderResponseDto expected = OrderResponseDto.builder()
                .totalPrice(80)
                .books(new ArrayList<BookResponseDto>(Arrays.asList(BookResponseDto.builder().name("test book 1").price(10).count(2).build(), BookResponseDto.builder().name("test book 2").price(20).count(3).build()))).build();
        var result = orderService.getOrderById(2);
        assertThat(result).usingRecursiveComparison().ignoringExpectedNullFields().ignoringActualNullFields().isEqualTo(expected);
    }

    @Test
    void getOrderById_whenOrderNotFound_thenShouldReturnNull() throws Exception {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        var result = orderService.getOrderById(2);
        verify(orderRepository, times(1)).findById(anyInt());
        assertThat(result).isNull();
    }
}