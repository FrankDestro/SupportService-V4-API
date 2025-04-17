package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.AttachmentDTO;
import com.dev.ServiceHelp.models.entities.Attachment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    AttachmentDTO toAttachmentDTO (Attachment attachment);

    Attachment toAttachmentEntity (AttachmentDTO attachmentDTO);


}
