package com.sample.ems.repository;

import com.sample.ems.entity.Employee;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    //@Query("select emp from employee emp where emp.email in (:emailList)")
    List<Employee> findAllByEmailIn(@Parameter(name = "emailList") List<String> emailList);
}
