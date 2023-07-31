package org.numo.dto.device;

import lombok.Data;
import org.numo.dto.company.CompanyDto;
import org.numo.dto.groupeDevice.DeviceGroupDto;
import org.numo.dto.reference.ReferenceDto;

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
