package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticsResponse;
import com.getir.ReadingIsGood.service.statistics.model.response.StatisticsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticsApiMapper {
    StatisticsResponse mapStatisticsResponseDtoToStatisticsResponse(StatisticsResponseDto order);
}
