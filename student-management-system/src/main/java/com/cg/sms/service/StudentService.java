package com.cg.sms.service;

import java.rmi.StubNotFoundException;
import java.util.List;

import com.cg.sms.entity.Student;

public interface StudentService {
	
	Student saveStudent(Student student, Long departmentId);
	
	List<Student> getAllStudents();
	
	Student getStudent(Long studentId);

	Student updateStudent(Student student, Long studentId);

	void deleteStudent(Long studentId) throws StubNotFoundException;
}
