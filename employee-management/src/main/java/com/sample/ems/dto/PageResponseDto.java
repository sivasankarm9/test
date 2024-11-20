package com.sample.ems.dto;

import com.sample.ems.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PageResponseDto {
    private Long total;
    private Integer pageNumber;
    private Integer size;
    private Integer numberOfPages;
    private List<EmployeeDto> employees;

}
