package com.getir.ReadingIsGood.controller.statistics;

import com.getir.ReadingIsGood.controller.statistics.model.StatisticViewModel;
import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.statistics.StatisticsService;
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

    public StatisticsController(StatisticsService statisticsService,
                                StatisticsApiMapper statisticsApiMapper) {
        this.statisticsService = statisticsService;
        this.statisticsApiMapper = statisticsApiMapper;
    }

    @GetMapping(value = "/customer/{customerId}")
    public GenericResponse<ArrayList<StatisticViewModel>> getStatistics(@PathVariable int customerId) {

        var result = statisticsService.getMonthlyStatistics(customerId);

        var response = new GenericResponse<ArrayList<StatisticViewModel>>();
        response.setSuccess(true);

        if (!result.isSuccess()) {
            response.setSuccess(false);
            response.setMessage(result.getMessage());
            return response;
        }

        var statistics = new ArrayList<StatisticViewModel>();

        for (var order : result.getData()) {
            statistics.add(statisticsApiMapper.mapStatisticsDtoToStatisticsViewModel(order));
        }

        response.setData(statistics);
        return response;
    }
}
