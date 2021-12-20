package com.getir.ReadingIsGood.repository.orderBooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderBooksRepository extends JpaRepository<OrderBook, Integer> {
    ArrayList<OrderBook> findAllByOrderId(int orderId);
}
