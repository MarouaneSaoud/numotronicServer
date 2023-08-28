package org.numo.api;

import lombok.AllArgsConstructor;
import org.numo.dto.StatisticsResponse;
import org.numo.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/last-nine-months")
    public List<StatisticsResponse> getLastNineMonthsStatistics() {
        return statisticsService.getStatistics();
    }
}
