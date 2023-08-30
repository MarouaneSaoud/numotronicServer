package org.numo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatistic {
    private int total ;
    private double active;
    private double offline ;
    private double inactive ;

}
