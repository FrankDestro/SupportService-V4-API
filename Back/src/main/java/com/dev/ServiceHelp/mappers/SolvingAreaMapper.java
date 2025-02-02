package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.SolvingAreaDTO;
import com.dev.ServiceHelp.models.entities.SolvingArea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolvingAreaMapper {

    SolvingAreaDTO toSolvingAreaDTO(SolvingArea solvingArea);
    SolvingArea toSolvingAreaEntity(SolvingAreaDTO solvingAreaDTO);
}
