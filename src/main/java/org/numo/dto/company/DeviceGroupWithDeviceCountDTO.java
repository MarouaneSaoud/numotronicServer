package org.numo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.numo.dao.entity.DeviceGroup;

@Data
@AllArgsConstructor
public class DeviceGroupWithDeviceCountDTO {
    private DeviceGroup deviceGroup;
    private Long deviceCount;

}
