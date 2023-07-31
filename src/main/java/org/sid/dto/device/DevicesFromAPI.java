package org.sid.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromAPI {

    private String IMEI;
    private String firware;
    private String config;
    private String lastSeen;


}
