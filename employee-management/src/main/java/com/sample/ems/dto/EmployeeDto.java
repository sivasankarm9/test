package com.sample.ems.dto;

import com.sample.ems.annotations.CompanyEmail;
import com.sample.ems.annotations.validator.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

import static com.sample.ems.constants.Constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmployeeDto {

    private Long id;
    @NotBlank(message = EMPLOYEE_NAME_VALIDATION_MESSAGE)
    private String name;

    @NotBlank(message = EMPLOYEE_EMAIL_VALIDATION_MESSAGE)
    //@Email(message = EMPLOYEE_EMAIL_FORMAT_VALIDATION_MESSAGE)
    @CompanyEmail(message = EMPLOYEE_COMPANY_EMAIL_FORMAT_VALIDATION_MESSAGE)
    //@UniqueEmail(message = EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE)
    //@Length(min = 15, max = 20)
    private String email;

    @NotNull
    @Min(value = 10000, message = EMPLOYEE_SALARY_VALIDATION_MESSAGE)
    private Double salary;

    private AddressDto address;
    private DepartmentDto department;
    private List<ProjectDto> projects;
}
