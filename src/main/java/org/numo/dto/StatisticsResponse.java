package org.numo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    private String month;
    private Long clientCount;
    private Long deviceCount;
    private Long companyCount;
}
