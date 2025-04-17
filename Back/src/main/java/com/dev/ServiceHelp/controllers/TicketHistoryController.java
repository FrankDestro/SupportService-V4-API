package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.shared.TicketHistoryDTO;
import com.dev.ServiceHelp.services.TicketHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/ticketHistory")
public class TicketHistoryController {

    private final TicketHistoryService ticketHistoryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PostMapping("/addHistory")
    @Operation(
            summary = "Adicionar um novo andamento para o ticket",
            description = "Retorna um ticketHistoryDTO após a inclusão")
    public ResponseEntity<TicketHistoryDTO> addTicketHistoryManually(@Valid @RequestBody TicketHistoryDTO ticketHistoryDTO) throws JsonProcessingException {
        ticketHistoryDTO = ticketHistoryService.addTicketHistoryManually(ticketHistoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ticketHistoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(ticketHistoryDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/{ticketId}")
    @Operation(
            summary = "Buscar histórico/ Andamento do ticket pelo id do mesmo",
            description = "Retorna o histórico de alterações de um ticket com paginação")
    public ResponseEntity<List<TicketHistoryDTO>> getTicketHistoryTicketById(
            @PathVariable Long ticketId) {
        List<TicketHistoryDTO> ticketHistoryDTO = ticketHistoryService.getTicketHistoryTicketById(ticketId);
        return ResponseEntity.ok().body(ticketHistoryDTO);
    }
}
