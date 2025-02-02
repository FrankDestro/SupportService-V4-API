package com.dev.ServiceHelp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTicketDTO {
    private Long id;
    private String name;
    private SolvingAreaDTO solvingAreaDTO;
}
