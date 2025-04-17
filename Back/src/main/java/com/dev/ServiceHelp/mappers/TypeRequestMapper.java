package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.TypeRequestDTO;
import com.dev.ServiceHelp.models.entities.TypeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeRequestMapper {

    TypeRequestDTO toTypeRequestDTO(TypeRequest typeRequest);

    TypeRequest toTypeRequestEntity(TypeRequestDTO typeRequestDTO);
}
