package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.UserDTO;
import com.dev.ServiceHelp.models.dto.UserUpdateDTO;
import com.dev.ServiceHelp.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO (User user);

    User toUserEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUserEntityFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

}
