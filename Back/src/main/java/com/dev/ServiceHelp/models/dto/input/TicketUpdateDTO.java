package com.dev.ServiceHelp.models.dto.input;

import com.dev.ServiceHelp.enums.StatusTicket;
import com.dev.ServiceHelp.models.dto.shared.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDTO {

    private StatusTicket statusTicket;
    private TypeRequestDTO typeRequest;
    private SLADTO sla;
    private SolvingAreaDTO solvingArea;
    private CategoryTicketDTO categoryTicket;
    private UserDTO technician;
    private UserDTO resolver;
}
