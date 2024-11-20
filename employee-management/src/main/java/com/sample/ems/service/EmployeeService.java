package com.sample.ems.service;

import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.PageResponseDto;
import com.sample.ems.dto.ProjectDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    PageResponseDto getEmployees(Pageable pageable);

    List<EmployeeDto> getEmployees();

    List<EmployeeDto> createEmployees(List<EmployeeDto> employees);

    EmployeeDto getEmployee(Long employeeId);

    AddressDto getEmployeeAddress(Long employeeId);

    List<ProjectDto> getEmployeeProjects(Long employeeId, String projectStatus);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, int employeeId);

    void deleteEmployee(int employeeId);

    PageResponseDto getEmployeesByCriteria(Pageable pageable, String name, String departmentName, String projectName, Long pinCode);
}
