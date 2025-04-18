package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.RoleDTO;
import com.dev.ServiceHelp.models.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);
    Role toRoleEntity(RoleDTO roleDTO);
}
