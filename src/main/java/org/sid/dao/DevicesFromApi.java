package org.sid.dao;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromApi {

    private String imei;
    private String reference;
    private String config;
    private String lastSeen;


}
