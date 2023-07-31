package org.sid.mappers;// ReferenceMapper.java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.dao.entity.Reference;
import org.sid.dto.reference.ReferenceDto;

@Mapper
public interface ReferenceMapper {
    ReferenceMapper INSTANCE = Mappers.getMapper(ReferenceMapper.class);

    @Mapping(target = "deviceList", ignore = true)
    ReferenceDto toDto(Reference reference);

    @Mapping(target = "deviceList", ignore = true)
    Reference toEntity(ReferenceDto referenceDto);
}
