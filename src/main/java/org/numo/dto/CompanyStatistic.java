package org.numo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyStatistic {
    private String monthName;
    private int deviceCount;
    private int clientCount;
}
