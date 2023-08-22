package org.numo.dto.groupeDevice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceToGroup {
    private String imei;
    private long group ;
}
