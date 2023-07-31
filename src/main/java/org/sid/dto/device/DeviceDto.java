package org.sid.dto.device;

import lombok.Data;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.DeviceGroup;
import org.sid.dao.entity.Reference;
import org.sid.dto.company.CompanyDto;
import org.sid.dto.groupeDevice.DeviceGroupDto;
import org.sid.dto.reference.ReferenceDto;

import java.util.Date;

@Data
public class DeviceDto {
    private Long id;
    private int serialNum;
    private int imei;
    private String description;
    private Date createdAt;
    private ReferenceDto reference;
    private CompanyDto company;
    private DeviceGroupDto deviceGroup;
}
