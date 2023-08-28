package org.numo.service;

import org.numo.dto.StatisticsResponse;

import java.util.List;


public interface StatisticsService {
     List<StatisticsResponse> getStatistics();
}
