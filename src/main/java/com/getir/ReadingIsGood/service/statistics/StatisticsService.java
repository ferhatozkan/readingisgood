package com.getir.ReadingIsGood.service.statistics;

import com.getir.ReadingIsGood.service.statistics.model.response.StatisticsResponseDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsResponseDto> getMonthlyStatistics(int customerId);
}
