package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.CategoryTicketDTO;
import com.dev.ServiceHelp.models.dto.TicketHistoryDTO;
import com.dev.ServiceHelp.services.TicketHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<TicketHistoryDTO>> getTicketHistoryTicketById(
            @PathVariable Long ticketId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TicketHistoryDTO> ticketHistoryDTO = ticketHistoryService.getTicketHistoryTicketById(ticketId, pageable);
        return ResponseEntity.ok().body(ticketHistoryDTO);
    }
}
