package org.sid.dao;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@JsonPropertyOrder({"IMEI","FIRMWARE","CONFIGUE","TIME"})
@AllArgsConstructor
@NoArgsConstructor
public class DevicesFromApi {

    private String IMEI;
    private String FIRMWARE;
    private String CONFIGUE;
    private String TIME;


}
