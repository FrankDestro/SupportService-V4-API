package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.KnowErrorDTO;
import com.dev.ServiceHelp.models.entities.KnowError;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KnowErrorMapper {

    @Mapping(target = "userID", source = "registratorUser.id")
    @Mapping(target = "userEmail", source = "registratorUser.email")
    KnowErrorDTO toKnowErrorDTO (KnowError knowError);

    KnowError toKnowErrorEntity (KnowErrorDTO knowErrorDTO);

}
