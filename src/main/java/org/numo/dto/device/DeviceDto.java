package org.numo.dto.device;

import lombok.Data;
import org.numo.dao.entity.Reference;
import org.numo.dto.reference.ReferenceDto;

import java.util.Date;

@Data
public class DeviceDto {
    private Long id;
    private int serialNum;
    private String imei;
    private String description;
    private Date createdAt;
    private Reference reference;
}
