package org.sid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromDTO {

    private String IMEI;
    private String reference;
    private String config;
    private String lastSeen;


}
