package com.dev.ServiceHelp.models.dto.output;

import com.dev.ServiceHelp.models.dto.shared.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String imgProfile;
    private String contactNumber;
    private DepartmentDTO department;
}
