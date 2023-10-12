package org.numo.api;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.numo.dao.CompanyStatistic;
import org.numo.dto.DeviceStatistic;
import org.numo.dto.StatisticsResponse;
import org.numo.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
@Api(tags = "statistics", description = "Endpoints to manage statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/statisticAdmin")
    public List<StatisticsResponse> getLastNineMonthsStatistics() {
        return statisticsService.getStatisticsForAdmin();
    }

    @GetMapping("/statisticDevices")
    public DeviceStatistic deviceStatistic() {
        return statisticsService.deviceStatistic();
    }
}
