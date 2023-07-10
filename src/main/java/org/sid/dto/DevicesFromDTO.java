package org.sid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromDTO {

    private String imei;
    private String reference;
    private String config;
    private String lastSeen;


}
