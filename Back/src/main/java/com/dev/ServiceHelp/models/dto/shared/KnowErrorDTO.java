package com.dev.ServiceHelp.models.dto.shared;

import com.dev.ServiceHelp.enums.StatusKnowError;
import com.dev.ServiceHelp.models.dto.output.AttachmentSimpleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KnowErrorDTO {

    private Long id;
    private String title;
    @Column(nullable = true)
    private String rootCause;
    @Column(nullable = true)
    private String solution;
    private List<String> tags;
    @Enumerated(EnumType.STRING)
    private StatusKnowError status;
    private LocalDate createDate;
    @Column(nullable = true)
    private LocalDate resolutionDate;

    private Long userID;
    private String userEmail;
    private List<AttachmentSimpleDTO> attachments = new ArrayList<>();

}
