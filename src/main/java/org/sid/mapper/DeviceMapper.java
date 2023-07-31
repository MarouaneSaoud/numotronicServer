package org.sid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.dao.entity.Device;
import org.sid.dto.device.DeviceDto;

@Mapper
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "deviceGroupId", source = "deviceGroup.id")
    DeviceDto deviceToDeviceDto(Device device);

    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "deviceGroup.id", source = "deviceGroupId")
    Device deviceDtoToDevice(DeviceDto deviceDto);
}
