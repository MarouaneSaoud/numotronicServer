package org.sid.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;
import org.sid.dto.appUser.AppUserDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToStrings")
    AppUserDto toDto(AppUser appUser);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapStringsToRoles")
    AppUser toEntity(AppUserDto appUserDto);

    default Collection<String> mapRolesToStrings(Collection<AppRole> roles) {
        return roles.stream().map(AppRole::getRoleName).collect(Collectors.toList());
    }

    default Collection<AppRole> mapStringsToRoles(Collection<String> roleNames) {
        return roleNames.stream().map(roleName -> new AppRole(null, roleName)).collect(Collectors.toList());
    }
}