package com.dev.ServiceHelp.models.dto.shared;

import com.dev.ServiceHelp.enums.StatusTicket;
import com.dev.ServiceHelp.models.dto.output.UserSimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private String subject;
    private String description;
    private Instant registrationDate;
    private Instant dueDate;
    private StatusTicket statusTicket;
    private Instant completionDate;
    private Long parentTicketId;
    private TypeRequestDTO typeRequest;
    private SLADTO sla;
    private SolvingAreaDTO solvingArea;
    private CategoryTicketDTO categoryTicket;
    private UserSimpleDTO requester;
    private UserSimpleDTO technician;
    private UserSimpleDTO resolver;
}


