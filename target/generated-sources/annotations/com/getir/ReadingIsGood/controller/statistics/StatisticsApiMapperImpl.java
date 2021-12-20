package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticViewModel;
import com.getir.ReadingIsGood.service.statistics.model.StatisticsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T12:24:31+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class StatisticsApiMapperImpl implements StatisticsApiMapper {

    @Override
    public StatisticViewModel mapStatisticsDtoToStatisticsViewModel(StatisticsDto order) {
        if ( order == null ) {
            return null;
        }

        StatisticViewModel statisticViewModel = new StatisticViewModel();

        return statisticViewModel;
    }
}
