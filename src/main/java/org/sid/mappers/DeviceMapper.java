package org.sid.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.dao.entity.Device;
import org.sid.dto.device.DeviceDto;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(target = "referenceId", source = "reference.id")
    DeviceDto toDto(Device device);
    @Mapping(target = "id", ignore = true)
    Device toEntity(DeviceDto deviceDto);
}
