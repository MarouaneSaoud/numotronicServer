package org.sid.dto.device;

import lombok.*;
import org.sid.model.StatusDevice;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceToSend {

    private Long id;
    private String imei;
    private String time;
    private StatusDevice statusDevice;
    private String firmware;
    private String configuration;
}
