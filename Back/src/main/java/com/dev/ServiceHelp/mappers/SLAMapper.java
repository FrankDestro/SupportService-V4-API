package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.SLADTO;
import com.dev.ServiceHelp.models.entities.SLA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SLAMapper {

    SLADTO toSLADTO(SLA sla);
    SLA toSLAEntity(SLADTO sladto);

    @Mapping(target = "id", ignore = true)
    void updateSlaEntityFromDTO(SLADTO sladto, @MappingTarget SLA sla);
}
