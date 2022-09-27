package com.cg.sms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sms.entity.Department;
import com.cg.sms.entity.Student;
import com.cg.sms.exception.DepartmentNotFoundException;
import com.cg.sms.repository.DepartmentReository;
import com.cg.sms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	DepartmentReository departmentReository;

	@Override
	public Department saveDepartment(Department deppartment) {
		return departmentReository.save(deppartment);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentReository.findAll();
	}

	@Override
	public Department getDepartment(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> depatmentOpt = departmentReository.findById(departmentId);
		if(depatmentOpt.isPresent()) {
			return depatmentOpt.get();
		}else {
			throw new DepartmentNotFoundException("Department is not fount");
		}
	}

	@Override
	public Department updateDepartment(Department department, Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> departmentOpt = departmentReository.findById(departmentId);
		Department dep = new Department();
		if(departmentOpt.isPresent()) {
			dep = departmentOpt.get();
			dep.setDepartmentHeadName(department.getDepartmentHeadName());
			dep.setName(department.getName());
			dep.setPhoneNumber(department.getPhoneNumber());
			return departmentReository.save(dep);
		}else {
			throw new DepartmentNotFoundException("Department is not fount");
		}
		
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		departmentReository.deleteById(departmentId);		
	}

}
