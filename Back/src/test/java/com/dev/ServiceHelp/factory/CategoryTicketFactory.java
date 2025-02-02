package com.dev.ServiceHelp.factory;

import com.dev.ServiceHelp.models.dto.CategoryTicketDTO;
import com.dev.ServiceHelp.models.entities.CategoryTicket;
import com.dev.ServiceHelp.mappers.CategoryTicketMapper;
import lombok.Setter;

public class CategoryTicketFactory {

    @Setter
    public static CategoryTicketMapper categoryTicketMapper;

    public static CategoryTicket createCategoryTicketEntity() {
        return new CategoryTicket(1L, "Access", SolvingAreaFactory.createSolvingAreaEntity());
    }

    public static CategoryTicketDTO createCategoryTicketDTO() {
        return new CategoryTicketDTO(1L, "Access", SolvingAreaFactory.createSolvingAreaDTO());
    }
}
