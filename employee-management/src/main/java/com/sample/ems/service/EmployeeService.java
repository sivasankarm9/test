package com.sample.ems.service;

import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.ProjectDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployees();

    List<EmployeeDto> createEmployees(List<EmployeeDto> employees);

    EmployeeDto getEmployee(Long employeeId);

    AddressDto getEmployeeAddress(Long employeeId);

    List<ProjectDto> getEmployeeProjects(Long employeeId, String projectStatus);
}
