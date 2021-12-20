package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticsResponse;
import com.getir.ReadingIsGood.service.statistics.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsApiMapper statisticsApiMapper;

    public StatisticsController(StatisticsService statisticsService, StatisticsApiMapper statisticsApiMapper) {
        this.statisticsService = statisticsService;
        this.statisticsApiMapper = statisticsApiMapper;
    }

    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<ArrayList<StatisticsResponse>> getStatistics(@PathVariable int customerId){

        var statisticsDtos = statisticsService.getMonthlyStatistics(customerId);
        var statistics = new ArrayList<StatisticsResponse>();
        for (var statisticDto : statisticsDtos) {
            statistics.add(statisticsApiMapper.mapStatisticsResponseDtoToStatisticsResponse(statisticDto));
        }
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }
}
