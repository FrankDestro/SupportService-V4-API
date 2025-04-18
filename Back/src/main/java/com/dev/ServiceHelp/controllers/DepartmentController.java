package com.dev.ServiceHelp.controllers;

import com.dev.ServiceHelp.models.dto.shared.DepartmentDTO;
import com.dev.ServiceHelp.services.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/getAllDepartment")
    @Operation(
            summary = "Busca todos os depatamentos em uma lista",
            description = "Retorna uma lista de DepartmentDTO")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() throws JsonProcessingException {
        List<DepartmentDTO> departmentList = departmentService.getAllDepartment();
        return ResponseEntity.ok().body(departmentList);
    }
}
