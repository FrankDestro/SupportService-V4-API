package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.shared.KnowErrorDTO;
import com.dev.ServiceHelp.services.KnowErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/knowError")
public class KnowErrorController {

    private final KnowErrorService knowErrorService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/getKnowErrorById/{id}")
    public ResponseEntity<KnowErrorDTO> getKnowErrorById(@PathVariable Long id) {
        KnowErrorDTO knowErrorDTO = knowErrorService.getKnowErrorById(id);
        return ResponseEntity.ok(knowErrorDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/getKnowErrorByCriteria")
    public ResponseEntity<Page<KnowErrorDTO>> getTicketsByCriteria(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "titleText", required = false) String titleText,
            @RequestParam(name = "rootCauseText", required = false) String rootCauseText,
            @RequestParam(name = "solution", required = false) String solution,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "initialDate", required = false) LocalDate initialDate,
            @RequestParam(name = "finalDate", required = false) LocalDate finalDate,
            @RequestParam(name = "initialDateResolution", required = false) LocalDate initialDateResolution,
            @RequestParam(name = "finalDateResolution", required = false) LocalDate finalDateResolution,
            Pageable pageable
            )
        {
            Page<KnowErrorDTO> list = knowErrorService.findKnowErrorsWithFilters(id, titleText, rootCauseText, solution, status, initialDate, finalDate,
                    initialDateResolution, finalDateResolution, pageable);
            return ResponseEntity.ok().body(list);
        }
    }
