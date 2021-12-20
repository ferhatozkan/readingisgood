package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticViewModel;
import com.getir.ReadingIsGood.service.statistics.model.StatisticsDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticsApiMapper {
    StatisticViewModel mapStatisticsDtoToStatisticsViewModel(StatisticsDto order);
}
