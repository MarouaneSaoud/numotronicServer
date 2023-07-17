package org.sid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.model.StatusDevice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceToSend {


    private String imei;
    private String time;
    private StatusDevice statusDevice;
    private String firmware;
    private String configuration;
}
