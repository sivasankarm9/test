package com.sample.ems.controller;

import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.ProjectDto;
import com.sample.ems.dto.ResponseDto;
import com.sample.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
@Tag(name = "Employees API")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Operation(description = "Get All Employees")
    @GetMapping(value = "/employees")
    public ResponseDto<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees = employeeService.getEmployees();
        return new ResponseDto<>(HttpStatus.OK.value(), employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseDto<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId) {
        EmployeeDto employees = employeeService.getEmployee(employeeId);
        return new ResponseDto<>(HttpStatus.OK.value(), employees);
    }

    @GetMapping("/employees/{id}/address")
    public ResponseDto<AddressDto> getEmployeeAddress(@PathVariable("id") Long employeeId) {
        AddressDto address = employeeService.getEmployeeAddress(employeeId);
        return new ResponseDto<>(HttpStatus.OK.value(), address);
    }

    @GetMapping("/employees/{id}/projects")
    public ResponseDto<List<ProjectDto>> getEmployeeProjects(@PathVariable("id") Long employeeId, @RequestParam(value = "status", required = false) String projectStatus) {
        List<ProjectDto> projects = employeeService.getEmployeeProjects(employeeId, projectStatus);
        return new ResponseDto<>(HttpStatus.OK.value(), projects);
    }

    @PostMapping("/employees")
    public ResponseDto<List<EmployeeDto>> createEmployees(@RequestBody @Valid List<EmployeeDto> employeeDtos) {
        List<EmployeeDto> employees = employeeService.createEmployees(employeeDtos);
        if(employees.stream().anyMatch(emp -> emp.getProjects().stream().anyMatch(project -> project.getStatus().equalsIgnoreCase("Completed"))) || employees.stream().anyMatch(e ->e.getAddress().getPinCode().equals("560001"))){

        }else {

        }
        return new ResponseDto<>(HttpStatus.CREATED.value(), employees);
    }


}
