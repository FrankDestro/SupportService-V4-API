package com.dev.ServiceHelp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorDTO {
        private Instant timestamp;
        private Integer status;
        private String error;
        private String path;

}