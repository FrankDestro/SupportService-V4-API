package com.dev.ServiceHelp.models.dto.output;

import com.dev.ServiceHelp.models.dto.shared.AttachmentDTO;
import com.dev.ServiceHelp.models.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentExtendedDTO extends AttachmentDTO {
    private Ticket ticket;
}
