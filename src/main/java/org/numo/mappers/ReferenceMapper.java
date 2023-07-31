package org.numo.mappers;// ReferenceMapper.java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.numo.dao.entity.Reference;
import org.numo.dto.reference.ReferenceDto;

@Mapper
public interface ReferenceMapper {
    ReferenceMapper INSTANCE = Mappers.getMapper(ReferenceMapper.class);

    @Mapping(target = "deviceList", ignore = true)
    ReferenceDto toDto(Reference reference);

    @Mapping(target = "deviceList", ignore = true)
    Reference toEntity(ReferenceDto referenceDto);
}
