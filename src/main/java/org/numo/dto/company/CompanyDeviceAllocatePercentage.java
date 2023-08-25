package org.numo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDeviceAllocatePercentage  {
    private Double affected;
    private Double notAffected;
}
