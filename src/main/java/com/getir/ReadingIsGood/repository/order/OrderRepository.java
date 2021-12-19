package com.getir.ReadingIsGood.repository.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    ArrayList<Order> findAllByCustomerId(int customerId, Pageable pageable);
    ArrayList<Order> findByCreatedOnBetween(Date start, Date end);
}
