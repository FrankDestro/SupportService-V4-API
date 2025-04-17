package com.dev.ServiceHelp.mappers;

import com.dev.ServiceHelp.models.dto.shared.DepartmentDTO;
import com.dev.ServiceHelp.models.entities.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartmentEntity(DepartmentDTO departmentDTO);

    DepartmentDTO toDepartmentDTO (Department department);

}


