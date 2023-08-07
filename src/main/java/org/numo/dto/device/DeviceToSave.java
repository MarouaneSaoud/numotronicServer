package org.numo.dto.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceToSave {
    private int serialNum;
    private String imei;
    private String description;
    private Long reference;

}
