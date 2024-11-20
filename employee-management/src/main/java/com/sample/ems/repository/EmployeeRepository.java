package com.sample.ems.repository;

import com.sample.ems.entity.Employee;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    //@Query("select emp from employee emp where emp.email in (:emailList)")
    List<Employee> findAllByEmailIn(@Parameter(name = "emailList") List<String> emailList);

/*
    @Query(value = """
            SELECT emp.* FROM EMPLOYEES emp
            INNER JOIN DEPARTMENT dept ON emp.EMP_ID = dept.ID
            LEFT JOIN EMPLOYEES_PROJECTS ep ON EMPLOYEE_EMP_ID = emp.EMP_ID
            LEFT JOIN EMP_PROJECTS proj ON ep.PROJECTS_ID = proj.ID
            LEFT JOIN ADDRESS add ON emp.ADDRESS_ID = add.ID
            WHERE emp.NAME = ?1 OR
            dept.NAME = ?2 OR
            proj.NAME = ?3 OR
            add.PIN_CODE = ?4
            """, nativeQuery = true)
    Page<Employee> findAllByCriteria(Pageable pageable,
                                     String name,
                                     String departmentName,
                                     String projectName,
                                     Long pinCode1);
*/

    /*@Query(value = """
            SELECT emp.* FROM EMPLOYEES emp
            INNER JOIN DEPARTMENT dept ON emp.EMP_ID = dept.ID
            LEFT JOIN EMPLOYEES_PROJECTS ep ON EMPLOYEE_EMP_ID = emp.EMP_ID
            LEFT JOIN EMP_PROJECTS proj ON ep.PROJECTS_ID = proj.ID
            LEFT JOIN ADDRESS add ON emp.ADDRESS_ID = add.ID
            WHERE emp.NAME = :name OR
            dept.NAME = :departmentName OR
            proj.NAME = :projectName OR
            add.PIN_CODE = :pinCode
            """, nativeQuery = true)
    Page<Employee> findAllByCriteria(Pageable pageable,
                                     @Param("name") String name,
                                     @Param("departmentName")String departmentName,
                                     @Param("projectName")String projectName,
                                     @Param("pinCode")Long pinCode);
*/
    @Query(value = """
                select emp from Employee emp 
                inner join emp.department dept
                left join emp.projects proj 
                left join emp.address add
                where  
                :name is null or emp.name = :name and
                :departmentName is null or dept.name = :departmentName and 
                :projectName is null or proj.name = :projectName and
                :pinCode is null or add.pinCode = :pinCode            
            """)
    Page<Employee> findAllByCriteria(Pageable pageable,
                                     @Param("name") String name,
                                     @Param("departmentName")String departmentName,
                                     @Param("projectName")String projectName,
                                     @Param("pinCode")Long pinCode);

}
