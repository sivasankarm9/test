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

import com.cg.sms.entity.Student;
import com.cg.sms.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Validated
	@PostMapping("/departments/{department-id}/students")
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student, @PathVariable("department-id") Long departmentId) {
		Student studentData = studentService.saveStudent(student, departmentId);
		return new ResponseEntity<>(studentData, HttpStatus.CREATED);
    }
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> studnents = studentService.getAllStudents();
		return new ResponseEntity<>(studnents, HttpStatus.OK);
	}
	
	@GetMapping("/students/{student-id}")
	public ResponseEntity<Student> getStudent(@PathVariable("student-id") Long studentId){
		Student studnent = studentService.getStudent(studentId);
		return new ResponseEntity<>(studnent, HttpStatus.OK);
	}
	
	
	@PutMapping("/students/{student-id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("student-id") Long studentId){
		Student updatedStudent = studentService.updateStudent(student, studentId);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}
	
	@DeleteMapping("/students/{student-id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("student-id") Long studentId) throws StubNotFoundException{
		studentService.deleteStudent(studentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
