package com.sample.ems.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.ProjectDto;
import com.sample.ems.entity.Employee;
import com.sample.ems.exceptions.EmployeeAlreadyExistsWirthEmail;
import com.sample.ems.exceptions.EmployeeNotFoundException;
import com.sample.ems.repository.EmployeeRepository;
import com.sample.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sample.ems.constants.Constants.EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE;

@Primary
@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<EmployeeDto> getEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDto> employeeDtos = employees.stream().map(emp ->
                objectMapper.convertValue(emp, EmployeeDto.class)
        ).toList();

        return employeeDtos;


    }

    @Override
    public List<EmployeeDto> createEmployees(List<EmployeeDto> employees) {
        List<EmployeeDto> savedEmployeeDtos = new ArrayList<>();
        List<Employee> employeeEntities = employees.stream().map(emp ->
                objectMapper.convertValue(emp, Employee.class)
        ).toList();

        List<String> emailList = employeeEntities.stream().map(Employee::getEmail).toList();

        List<Employee> duplicateEmailEmployees = employeeRepository.findAllByEmailIn(emailList);
        List<String> duplicateEmails = duplicateEmailEmployees.stream().map(Employee::getEmail).toList();
        if (duplicateEmails.isEmpty()) {
            List<Employee> savedEmployees = employeeRepository.saveAll(employeeEntities);
            savedEmployeeDtos = getEmployeeDtos(savedEmployees);
        } else {
            employeeEntities = employeeEntities.stream().filter(emp -> !duplicateEmails.contains(emp.getEmail())).toList();
            List<Employee> savedEmployees = employeeRepository.saveAll(employeeEntities);
            savedEmployeeDtos = getEmployeeDtos(savedEmployees);
            throw new EmployeeAlreadyExistsWirthEmail(EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE + " :: " + duplicateEmailEmployees.stream().map(Employee::getEmail).toList());
        }

        /*List<String> emails = new ArrayList<>();
        employees.forEach(emp -> {
            Optional<Employee> empOpt = employeeRepository.findByEmail(emp.getEmail());
            if(empOpt.isPresent()){
                emails.add(emp.getEmail());
            }
        });
        if(emails.isEmpty()){
            List<Employee> savedEmployees = employeeRepository.saveAll(employeeEntities);
            return savedEmployees.stream().map(emp ->
                    objectMapper.convertValue(emp, EmployeeDto.class)
            ).toList();

        }else{
            throw new EmployeeAlreadyExistsWirthEmail(EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE+" :: "+emails);
        }*/
        return savedEmployeeDtos;
    }

    private List<EmployeeDto> getEmployeeDtos(List<Employee> savedEmployees) {
        List<EmployeeDto> savedEmployeeDtos;
        savedEmployeeDtos = savedEmployees.stream().map(emp ->
                objectMapper.convertValue(emp, EmployeeDto.class)
        ).toList();
        return savedEmployeeDtos;
    }

    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        EmployeeDto empDto = new EmployeeDto();
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            empDto = objectMapper.convertValue(emp, EmployeeDto.class);
        } else {
            throw new EmployeeNotFoundException("Employee not found for id : " + employeeId);
        }
        return empDto;
    }

    @Override
    public AddressDto getEmployeeAddress(Long employeeId) {
        EmployeeDto empDto = new EmployeeDto();
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            empDto = objectMapper.convertValue(emp, EmployeeDto.class);
        }
        return empDto.getAddress();
    }

    @Override
    public List<ProjectDto> getEmployeeProjects(Long employeeId, String projectStatus) {
        EmployeeDto empDto = new EmployeeDto();
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            empDto = objectMapper.convertValue(emp, EmployeeDto.class);
        }
        return projectStatus != null && !projectStatus.equals("") ? empDto.getProjects().stream().filter(project -> project.getStatus().equalsIgnoreCase(projectStatus)).toList() : empDto.getProjects();
    }
}
