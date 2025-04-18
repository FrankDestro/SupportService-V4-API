package com.dev.ServiceHelp.factory;

import com.dev.ServiceHelp.models.dto.shared.SolvingAreaDTO;
import com.dev.ServiceHelp.models.entities.SolvingArea;
import com.dev.ServiceHelp.mappers.SolvingAreaMapper;
import lombok.Setter;
import java.util.Collections;
import java.util.List;

public class SolvingAreaFactory {

    @Setter
    private static SolvingAreaMapper solvingAreaMapper;

    public static SolvingArea createSolvingAreaEntity() {
        return new SolvingArea();
    }

    public static SolvingAreaDTO createSolvingAreaDTO() {
        return createSolvingAreaDTO(createSolvingAreaEntity());
    }

    public static SolvingAreaDTO createSolvingAreaDTO(SolvingArea solvingArea) {
        return new SolvingAreaDTO(1l, "sistemas");
    }

    public static List<SolvingArea> createSolvingAreaEntityList() {
        return createSingletonList(createSolvingAreaEntity());
    }

    public static List<SolvingAreaDTO> createSolvingAreaDTOList() {
        return createSingletonList(createSolvingAreaDTO());
    }

    private static <T> List<T> createSingletonList(T object) {
        return Collections.singletonList(object);
    }
}
