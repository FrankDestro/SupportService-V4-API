package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.AttachmentDTO;
import com.dev.ServiceHelp.models.dto.TicketHistoryDTO;
import com.dev.ServiceHelp.services.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/add")
    @Operation(
            summary = "Adiciona um novo anexo ao ticket",
            description = "Retorna o AttachmentDTO após sua inclusão")
    public ResponseEntity<AttachmentDTO> createAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        AttachmentDTO savedAttachment = attachmentService.saveAttachment(attachmentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAttachment.getId()).toUri();
        return ResponseEntity.created(uri).body(savedAttachment);
    }

    @GetMapping("/{ticketId}")
    @Operation(
            summary = "Busca os anexos do ticket pelo id",
            description = "Retorna os aneos do ticket com paginação")
    public ResponseEntity<Page<AttachmentDTO>> getAttachmentsTicketById(
            @PathVariable Long ticketId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<AttachmentDTO> attachmentDTOS = attachmentService.getAttachmentTicketById(ticketId, pageable);
        return ResponseEntity.ok().body(attachmentDTOS);
    }
}
