package com.dev.ServiceHelp.models.dto.shared;

import com.dev.ServiceHelp.enums.NoteType;
import com.dev.ServiceHelp.models.dto.output.UserSimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TicketHistoryDTO {

    private Long id;
    private String description;
    private boolean annotationPublic;
    private Instant registrationDate;
    private boolean visibleToRequester;
    private boolean systemGenerated;
    private NoteType noteType;
    private long ticketId;
    private UserSimpleDTO user;
}

