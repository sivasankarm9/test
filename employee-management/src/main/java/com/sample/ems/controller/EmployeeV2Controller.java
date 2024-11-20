package com.sample.ems.controller;

import com.sample.ems.dto.PageResponseDto;
import com.sample.ems.dto.ResponseDto;
import com.sample.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sample.ems.constants.Constants.PAGE_DEFAULT_NUMBER;
import static com.sample.ems.constants.Constants.PAGE_DEFAULT_SIZE;

@RestController
@RequestMapping("emp/v2")
@Tag(name = "Employees V2 API")
@CrossOrigin("*")
public class EmployeeV2Controller {

    @Autowired
    EmployeeService employeeService;


    @Operation(description = "Get All Employees")
    @GetMapping(value = "/employees")
    public ResponseDto<PageResponseDto> getEmployees(@PageableDefault(size = PAGE_DEFAULT_SIZE, page = PAGE_DEFAULT_NUMBER, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        PageResponseDto response = employeeService.getEmployees(pageable);
        return new ResponseDto<>(HttpStatus.OK.value(), response);
    }

    @Operation(description = "Get All Employees")
    @GetMapping(value = "/customized-employees")
    public ResponseDto<PageResponseDto> getEmployeesByCriteria(@PageableDefault(size = PAGE_DEFAULT_SIZE, page = PAGE_DEFAULT_NUMBER, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String departmentName,
                                                               @RequestParam(required = false) String projectName,
                                                               @RequestParam(required = false) Long pinCode) {
        PageResponseDto response = employeeService.getEmployeesByCriteria(pageable, name, departmentName, projectName, pinCode);
        return new ResponseDto<>(HttpStatus.OK.value(), response);
    }
}
