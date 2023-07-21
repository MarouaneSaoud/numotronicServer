package org.sid.dto;

import lombok.*;
import org.sid.model.StatusDevice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceToSend {


    private String imei;
    private String time;
    private StatusDevice statusDevice;
    private String firmware;
    private String configuration;
}
