package com.getir.ReadingIsGood.service.statistics;

import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.statistics.model.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    GenericResponse<List<StatisticsDto>> getMonthlyStatistics(int customerId);
}
