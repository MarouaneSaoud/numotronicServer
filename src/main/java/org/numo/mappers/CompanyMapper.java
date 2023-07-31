package org.numo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.numo.dao.entity.Company;
import org.numo.dto.company.CompanyDto;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "account", target = "accountDto")
    CompanyDto toDto(Company company);

    @Mapping(source = "accountDto", target = "account")
    Company toEntity(CompanyDto companyDto);
}