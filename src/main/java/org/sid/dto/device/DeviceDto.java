package org.sid.dto.device;

import lombok.Data;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.DeviceGroup;
import org.sid.dao.entity.Reference;

import java.util.Date;

@Data
public class DeviceDto {
    private Long id;
    private int serialNum;
    private int imei;
    private String description;
    private Date createdAt;
    private Reference reference;
    private Company company;
    private DeviceGroup deviceGroup;
}
