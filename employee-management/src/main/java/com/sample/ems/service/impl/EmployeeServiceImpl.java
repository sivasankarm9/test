package com.sample.ems.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.ems.dto.AddressDto;
import com.sample.ems.dto.EmployeeDto;
import com.sample.ems.dto.PageResponseDto;
import com.sample.ems.dto.ProjectDto;
import com.sample.ems.entity.Address;
import com.sample.ems.entity.Department;
import com.sample.ems.entity.Employee;
import com.sample.ems.entity.Project;
import com.sample.ems.exceptions.EmployeeNotFoundException;
import com.sample.ems.repository.EmployeeRepository;
import com.sample.ems.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sample.ems.constants.Constants.EMPLOYEE_NOT_FOUND_ERROR_DESCRIPTION_MESSAGE;

@Log4j2
@Primary
@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EntityManager entityManager;

    private final EmployeeRepository employeeRepository;

    private final ObjectMapper objectMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PageResponseDto getEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeDto> employeeDtos = employees.get().toList().stream().map(emp ->
                objectMapper.convertValue(emp, EmployeeDto.class)
        ).toList();

        return PageResponseDto.builder()
                .total(employees.getTotalElements())
                .size(pageable.getPageSize())
                .pageNumber(pageable.getPageNumber())
                .employees(employeeDtos)
                .numberOfPages(employees.getTotalPages())
                .build();
    }

    @Override
    public List<EmployeeDto> getEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(emp ->
                objectMapper.convertValue(emp, EmployeeDto.class)
        ).toList();
    }

    @Override
    public List<EmployeeDto> createEmployees(List<EmployeeDto> employees) {
        List<Employee> employeeEntities = employees.stream().map(emp ->
                objectMapper.convertValue(emp, Employee.class)
        ).toList();
        List<Employee> savedEmployees = employeeRepository.saveAll(employeeEntities);
        return getEmployeeDtos(savedEmployees);

      /*  List<String> emailList = employeeEntities.stream().map(Employee::getEmail).toList();

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
*/
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
        //return savedEmployeeDtos;
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
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            return objectMapper.convertValue(emp, EmployeeDto.class);
        } else {
            throw new EmployeeNotFoundException("Employee not found for id : " + employeeId);
        }
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
        return projectStatus != null && !projectStatus.isEmpty() ? empDto.getProjects().stream().filter(project -> project.getStatus().equalsIgnoreCase(projectStatus)).toList() : empDto.getProjects();
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, int employeeId) {
        Employee empEntity = objectMapper.convertValue(employeeDto, Employee.class);
        Optional<Employee> employeeOpt = employeeRepository.findById((long) employeeId);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setName(empEntity.getName());
            employee.setSalary(empEntity.getSalary());
            employee.setEmail(empEntity.getEmail());
            employee.getAddress().setAddress1(empEntity.getAddress().getAddress1());
            employee.getAddress().setAddress2(empEntity.getAddress().getAddress2());
            employee.getAddress().setState(empEntity.getAddress().getState());
            employee.getAddress().setPinCode(empEntity.getAddress().getPinCode());
            employee.getDepartment().setName(empEntity.getDepartment().getName());
            employee.getProjects().forEach(project -> {
                Project pr = empEntity.getProjects().stream().filter(proj -> proj.getId().equals(project.getId())).findFirst().get();
                project.setName(pr.getName());
                project.setDuration(pr.getDuration());
                project.setStatus(pr.getStatus());
                project.setEndDate(pr.getEndDate());
                project.setStartDate(pr.getStartDate());
            });

            List<Project> notMatchedProjects = empEntity.getProjects().stream().filter(project -> project.getId() == null).toList();

            employee.getProjects().addAll(notMatchedProjects);

            Employee updatedEmployee = employeeRepository.save(employee);

            return objectMapper.convertValue(employee, EmployeeDto.class);
        } else {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_ERROR_DESCRIPTION_MESSAGE);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Optional<Employee> empOpt = employeeRepository.findById((long) employeeId);
        if (empOpt.isPresent()) {
            employeeRepository.delete(empOpt.get());
        } else {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_ERROR_DESCRIPTION_MESSAGE);
        }
    }

    /*@Override
    public PageResponseDto getEmployeesByCriteria(Pageable pageable, String name, String departmentName, String projectName, Long pinCode) {
        Page<Employee> employees = employeeRepository.findAllByCriteria(pageable, name, departmentName, projectName, pinCode);
        List<EmployeeDto> employeeDtos = employees.get().toList().stream().map(emp ->
                objectMapper.convertValue(emp, EmployeeDto.class)
        ).toList();

        return PageResponseDto.builder()
                .total(employees.getTotalElements())
                .size(pageable.getPageSize())
                .pageNumber(pageable.getPageNumber())
                .employees(employeeDtos)
                .numberOfPages(employees.getTotalPages())
                .build();
    }*/

    @Override
    public PageResponseDto getEmployeesByCriteria(Pageable pageable, String name, String departmentName, String projectName, Long pinCode) {

        CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> empQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> emp = empQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isBlank(name)){
            predicates.add(criteriaBuilder.equal(emp.get("name"),name));
        }

       if(!StringUtils.isBlank(departmentName)){
           Path<String> department = emp.get("department").get("name");
           predicates.add(criteriaBuilder.equal(department, departmentName));
        }

        /*if(!StringUtils.isBlank(projectName)){
            Predicate projNamePredicate = criteriaBuilder.equal(emp.get("projects.name"),projectName);
            empQuery.where(projNamePredicate);
        }

         */
        if(pinCode != null){
            Path<String> pinCodeNo = emp.get("address").get("pinCode");
            predicates.add(criteriaBuilder.equal(pinCodeNo,pinCode));
        }

        empQuery.select(emp).where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        List<Employee> employeeList =  entityManager.createQuery(empQuery).getResultList();
        TypedQuery typedQuery = entityManager.createQuery(empQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());


        List<Employee> employees = typedQuery.getResultList();

        List<EmployeeDto> employeeDtos = employees.stream().map(emp1 ->
                objectMapper.convertValue(emp1, EmployeeDto.class)
        ).toList();


        double d = (double) employeeList.size()/pageable.getPageSize();
        int noOfPages = (int)Math.ceil(d);

        return PageResponseDto.builder()
                .total((long)employeeList.size())
                .size(pageable.getPageSize())
                .pageNumber(pageable.getPageNumber())
                .employees(employeeDtos)
                .numberOfPages(noOfPages)
                .build();
    }

}
