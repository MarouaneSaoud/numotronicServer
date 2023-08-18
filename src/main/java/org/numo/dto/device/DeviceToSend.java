package org.numo.dto.device;

import lombok.*;
import org.numo.dao.entity.Company;
import org.numo.model.StatusDevice;

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
    private String company ;
    private String client ;
    private String group;
}
