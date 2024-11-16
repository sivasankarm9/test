package com.sample.ems.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.sample.ems.constants.Constants.DEPARTMENT_NAME_VALIDATION_MESSAGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class DepartmentDto {

    private Long id;

    @NotBlank(message = DEPARTMENT_NAME_VALIDATION_MESSAGE)
    private String name;
}
