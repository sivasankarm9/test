package com.cg.sms.service.impl;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sms.entity.Department;
import com.cg.sms.entity.Student;
import com.cg.sms.exception.DepartmentNotFoundException;
import com.cg.sms.exception.StudentNotFoundException;
import com.cg.sms.repository.DepartmentReository;
import com.cg.sms.repository.StudentRepository;
import com.cg.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	DepartmentReository departmentReository;
	
	
	@Override
	public Student saveStudent(Student student, Long departmentId) {
		Optional<Department> departmentOpt =  departmentReository.findById(departmentId);
		if(departmentOpt.isPresent()) {
			Department department = departmentOpt.get();
			student.setDepartment(department);
			return studentRepository.save(student);
		}else {
			throw new DepartmentNotFoundException("Department not found");
		}
		
	}


	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}


	@Override
	public Student getStudent(Long studentId) throws StudentNotFoundException {
		
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		if(studentOpt.isPresent()) {
			return studentOpt.get();
		}else {
			throw new StudentNotFoundException("Student with ID:: "+studentId+" Not Found");
		}				
				
	}


	@Override
	public Student updateStudent(Student student, Long studentId) {
		Optional<Student> studentOpt = studentRepository.findById(studentId);
		if(studentOpt.isPresent()) {
			Student studentInfo = studentOpt.get();
			//studentInfo.setDepearmentName(student.getDepearmentName());
			studentInfo.setEmail(student.getEmail());
			studentInfo.setFirstName(student.getFirstName());
			studentInfo.setLastName(student.getLastName());
			studentInfo.setPhoneNumber(student.getPhoneNumber());
			return studentRepository.save(studentInfo);
		}else {
			throw new StudentNotFoundException("Student with ID:: "+studentId+" Not Found");
		}	
		 
	}


	@Override
	public void deleteStudent(Long studentId) {
		studentRepository.deleteById(studentId);
	}

}
