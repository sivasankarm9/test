package com.sample.ems.controller;

import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.ProjectDto;
import com.sample.ems.dto.ResponseDto;
import com.sample.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("emp")
@Tag(name = "Employees API")
@CrossOrigin("*")
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    public ResponseDto<List<EmployeeDto>> createEmployees(@RequestBody @Valid List<EmployeeDto> employeeDtos) {
        List<EmployeeDto> employees = employeeService.createEmployees(employeeDtos);
        return new ResponseDto<>(HttpStatus.CREATED.value(), employees);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/employees/{id}")
    public ResponseDto<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("id") int employeeId){
        EmployeeDto employee = employeeService.updateEmployee(employeeDto, employeeId);
        return new ResponseDto<>(HttpStatus.OK.value(), employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employees/{id}")
    public ResponseDto<EmployeeDto> deleteEmployee(@PathVariable("id") int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseDto<>(HttpStatus.NO_CONTENT.value(), null);
    }

}
