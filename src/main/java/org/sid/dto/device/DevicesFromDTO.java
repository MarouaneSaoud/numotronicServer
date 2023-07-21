package org.sid.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromDTO {

    private String IMEI;
    private String firware;
    private String config;
    private String lastSeen;


}
