package org.sid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.dao.entity.Reference;

@Getter
@Setter
@AllArgsConstructor
public class DeviceToSave {

    private int serialNum;
    private int imei;
    private String description;
    private Long reference;

}
