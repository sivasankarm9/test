package com.cg.sms.controller;

import java.rmi.StubNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sms.entity.Department;
import com.cg.sms.entity.Student;
import com.cg.sms.service.DepartmentService;
import com.cg.sms.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@Validated
	@PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@RequestBody @Valid Department department) {
		Department departmentData = departmentService.saveDepartment(department);
		return new ResponseEntity<>(departmentData, HttpStatus.CREATED);
    }
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDepartments(){
		List<Department> departments = departmentService.getAllDepartments();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@GetMapping("/departments/{department-id}")
	public ResponseEntity<Department> getDepartment(@PathVariable("department-id") Long departmentId){
		Department department = departmentService.getDepartment(departmentId);
		return new ResponseEntity<>(department, HttpStatus.OK);
	}
	
	
	@PutMapping("/departments/{department-id}")
	public ResponseEntity<Department> updateDepatment(@RequestBody Department department, @PathVariable("department-id") Long departmentId){
		Department updatedDepartment = departmentService.updateDepartment(department, departmentId);
		return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{department-id}")
	public ResponseEntity<Department> updateStudent(@PathVariable("department-id") Long departmentId) throws StubNotFoundException{
		departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
