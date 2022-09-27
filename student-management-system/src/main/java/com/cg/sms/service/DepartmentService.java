package com.cg.sms.service;

import java.rmi.StubNotFoundException;
import java.util.List;

import com.cg.sms.entity.Department;
import com.cg.sms.entity.Student;
import com.cg.sms.exception.DepartmentNotFoundException;

public interface DepartmentService {
	
    Department saveDepartment(Department department);
	
	List<Department> getAllDepartments();
	
	Department getDepartment(Long departmentId) throws DepartmentNotFoundException;

	Department updateDepartment(Department department, Long departmentId) throws DepartmentNotFoundException;

	void deleteDepartment(Long departmentId);

}
