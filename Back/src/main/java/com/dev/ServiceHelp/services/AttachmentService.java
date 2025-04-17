package com.dev.ServiceHelp.services;

import com.dev.ServiceHelp.models.dto.shared.AttachmentDTO;
import com.dev.ServiceHelp.models.entities.Attachment;
import com.dev.ServiceHelp.models.entities.Ticket;
import com.dev.ServiceHelp.mappers.AttachmentMapper;
import com.dev.ServiceHelp.repository.AttachmentRepository;
import com.dev.ServiceHelp.repository.TicketRepository;
import com.dev.ServiceHelp.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttachmentService {

    private final UserService userService;

    private final AttachmentRepository attachmentRepository;
    private final TicketRepository ticketRepository;

    private final AttachmentMapper attachmentMapper;

    @Transactional
    public AttachmentDTO saveAttachment(AttachmentDTO attachmentDTO) {

        Ticket ticket = ticketRepository.findById(attachmentDTO.getTicketId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("ticket not found"));

        Attachment attachment = attachmentMapper.toAttachmentEntity(attachmentDTO);
        attachment.setTicket(ticket);
        attachment.setUser(userService.authenticated());
        attachment = attachmentRepository.save(attachment);
        return attachmentMapper.toAttachmentDTO(attachment);
    }

    @org.springframework.transaction.annotation.Transactional
    public List<AttachmentDTO> getAttachmentTicketById(Long ticketId) {
        List<Attachment> attachments = attachmentRepository.findByTicketIdNative(ticketId);
        List<AttachmentDTO> dtoPage = attachments.stream().map(attachmentMapper::toAttachmentDTO).toList();
        return dtoPage;
    }
}
