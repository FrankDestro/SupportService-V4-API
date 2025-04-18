package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.CategoryTicketDTO;
import com.dev.ServiceHelp.models.entities.CategoryTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryTicketMapper {


    @Mapping(target = "solvingAreaDTO", source = "solvingArea")
    CategoryTicketDTO toCategoryTicketDTO(CategoryTicket categoryTicket);
    CategoryTicket toCategoryTicketEntity(CategoryTicketDTO categoryTicketDTO);

    @Mapping(target = "solvingAreaDTO", ignore = true)
    CategoryTicketDTO toCategoryTicketDTOSimple(CategoryTicket categoryTicket);
}
