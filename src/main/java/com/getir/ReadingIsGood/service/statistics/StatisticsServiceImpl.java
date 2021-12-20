package com.getir.ReadingIsGood.service.statistics;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import com.getir.ReadingIsGood.repository.statistics.StatisticsRepository;
import com.getir.ReadingIsGood.service.statistics.model.response.StatisticsResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public ArrayList<StatisticsResponseDto> getMonthlyStatistics(int customerId){

        if (customerId < 1) throw new BusinessException("CustomerId must be greater than or equal to 1");

        var statistics = statisticsRepository.findCustomerStatistic(customerId);

        var statisticList = new ArrayList<StatisticsResponseDto>();
        for (var statistic: statistics) {
            var month = (String) statistic.get(0);
            var totalAmount = (Double) statistic.get(1);
            var totalOrderCount = (BigInteger) statistic.get(2);
            var tempStatistic = StatisticsResponseDto.builder().month(month).totalPurchasedAmount(totalAmount).totalOrderCount(totalOrderCount.intValue()).build();
            statisticList.add(tempStatistic);
        }

        return statisticList;
    }
}
