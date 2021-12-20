package com.getir.ReadingIsGood.service.statistics;

import com.getir.ReadingIsGood.repository.order.OrderRepository;
import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.statistics.model.StatisticsDto;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderRepository orderRepository;

    public StatisticsServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public GenericResponse<List<StatisticsDto>> getMonthlyStatistics(int customerId) {

        var response = new GenericResponse<List<StatisticsDto>>();
        response.setSuccess(true);

        if (customerId < 1) {
            response.setMessage("CustomerId must be greater than or equal to 1");
            response.setSuccess(false);
            return response;
        }

        var orders = orderRepository.findAllByCustomerId(customerId);
        var uniqueMonths = new ArrayList<Month>();
        for (var order : orders) {
            var month = Month.from(order.getCreatedOn().toInstant());
            if (!uniqueMonths.contains(month))
                uniqueMonths.add(month);
        }


        for (var month : uniqueMonths.toArray()) {
            var monthlyOrders = orders.stream()
                    .filter(n -> Month.from(n.getCreatedOn().toInstant()) == month)
                    .collect(Collectors.toList());

            var temp = "4sd";
        }


//
//        var statistic = new StatisticsDto();
//        var month =
//
//        response.setData();
        response.setSuccess(false);
        return response;
    }
}
