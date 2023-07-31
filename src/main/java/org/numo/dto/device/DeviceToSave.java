package org.numo.dto.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceToSave {
    private int serialNum;
    private int imei;
    private String description;
    private Long reference;

}
