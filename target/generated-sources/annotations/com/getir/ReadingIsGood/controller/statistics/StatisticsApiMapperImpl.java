package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticsResponse;
import com.getir.ReadingIsGood.controller.statistics.model.StatisticsResponse.StatisticsResponseBuilder;
import com.getir.ReadingIsGood.service.statistics.model.response.StatisticsResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-20T23:10:00+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class StatisticsApiMapperImpl implements StatisticsApiMapper {

    @Override
    public StatisticsResponse mapStatisticsResponseDtoToStatisticsResponse(StatisticsResponseDto order) {
        if ( order == null ) {
            return null;
        }

        StatisticsResponseBuilder statisticsResponse = StatisticsResponse.builder();

        statisticsResponse.month( order.getMonth() );
        statisticsResponse.totalOrderCount( order.getTotalOrderCount() );
        statisticsResponse.totalBookCount( order.getTotalBookCount() );
        statisticsResponse.totalPurchasedAmount( order.getTotalPurchasedAmount() );

        return statisticsResponse.build();
    }
}
