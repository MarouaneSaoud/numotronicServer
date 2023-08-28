package org.numo.service.impl;

import lombok.AllArgsConstructor;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dto.StatisticsResponse;
import org.numo.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final DeviceRepository deviceRepository;


    @Override
    public List<StatisticsResponse> getStatistics() {
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

private Date getNineMonthsAgoDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nineMonthsAgo = currentDate.minusMonths(9);
        return Date.from(nineMonthsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    private String getMonthInLetters(int monthNumber) {
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        return symbols.getMonths()[monthNumber - 1];
    }
}



