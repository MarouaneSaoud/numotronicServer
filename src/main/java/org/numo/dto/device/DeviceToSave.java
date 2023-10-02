package org.numo.dto.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class DeviceToSave {
    private String serialNum;
    private String imei;
    private String description;
    private Long reference;

}
