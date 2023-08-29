package org.numo.service.impl;

import lombok.AllArgsConstructor;
import org.numo.dao.CompanyStatistic;
import org.numo.dao.entity.Company;
import org.numo.dao.repository.CompanyRepository;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dto.StatisticsResponse;
import org.numo.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final DeviceRepository deviceRepository;
    private final CompanyRepository companyRepository;


    @Override
    public List<StatisticsResponse> getStatisticsForAdmin() {
        Date nineMonthsAgo = getNineMonthsAgoDate();
        List<Object[]> results = deviceRepository.getStatistics(nineMonthsAgo);

        List<StatisticsResponse> responseList = new ArrayList<>();
        for (Object[] result : results) {
            Integer monthNumber = (Integer) result[0];
            Long clientCount = (Long) result[1];
            Long deviceCount = (Long) result[2];
            Long companyCount = (Long) result[3];

            String monthInLetters = getMonthInLetters(monthNumber);

            StatisticsResponse response = new StatisticsResponse();
            response.setMonth(monthInLetters);
            response.setClientCount(clientCount);
            response.setDeviceCount(deviceCount);
            response.setCompanyCount(companyCount);

            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public List<CompanyStatistic> getDeviceAndClientCountsForCompanyAndPreviousMonths(String email) {
        Date startDate = getNineMonthsAgoDate();
        Company company = companyRepository.findByAccount_Username(email);

        List<Object[]> results = companyRepository.getDeviceAndClientCountsForCompanyAndMonths(company.getId(), startDate);

        List<CompanyStatistic> dtos = new ArrayList<>();

        for (Object[] result : results) {
            Date createdAt = (Date) result[0];
            int deviceCount = ((Number) result[1]).intValue();
            int clientCount = ((Number) result[2]).intValue();

            CompanyStatistic dto = new CompanyStatistic();
            dto.setMonthName(getMonthInLetters(createdAt.getMonth() + 1));
            dto.setDeviceCount(deviceCount);
            dto.setClientCount(clientCount);

            dtos.add(dto);
        }

        return dtos;
    }

    private Date getNineMonthsAgoDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nineMonthsAgo = currentDate.minusMonths(9);
        return Date.from(nineMonthsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    private String getMonthInLetters(int monthNumber) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
        return symbols.getMonths()[monthNumber - 1];
    }
}



