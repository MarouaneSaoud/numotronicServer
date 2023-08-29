package org.numo.service;

import org.numo.dao.CompanyStatistic;
import org.numo.dto.StatisticsResponse;

import java.util.List;


public interface StatisticsService {
     List<StatisticsResponse> getStatisticsForAdmin();

     public List<CompanyStatistic> getDeviceAndClientCountsForCompanyAndPreviousMonths(String companyId);
}
