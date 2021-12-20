package com.getir.ReadingIsGood.repository.statistics;

import com.getir.ReadingIsGood.repository.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Order, Integer> {

    @Query( value = " SELECT MONTHNAME(CREATED_ON) as MonthName, SUM(TOTAL_PRICE) as TotalAmount , COUNT(ID) as TotalOrderCount" +
            " FROM \"order\"  " +
            " GROUP BY MONTHNAME(CREATED_ON) ",
            nativeQuery = true)
    List<Tuple> findCustomerStatistic(int customerId);
}
