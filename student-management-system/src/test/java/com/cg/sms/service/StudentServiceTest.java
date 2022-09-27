package com.cg.sms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.sms.entity.Student;
import com.cg.sms.repository.StudentRepository;
import com.cg.sms.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	
	@Mock
	StudentRepository studentRepository;
	
	@InjectMocks
	StudentServiceImpl studentService;
	
	
	@Test
	void testGetAllStudents() {
		
		List<Student> studentsMockData = createStudentsMockData();
		
		when(studentRepository.findAll()).thenReturn(studentsMockData);
		
		List<Student> students = studentService.getAllStudents();
		
		assert(students.size() == studentsMockData.size());
		assert(students.get(0).getFirstName().equals(studentsMockData.get(0).getFirstName()));
		
	}
	
	
	private List<Student> createStudentsMockData(){
		List<Student> students = new ArrayList<>();
		
		Student student = new Student();
		student.setEmail("ramesh@gmail.com");
		student.setFirstName("Ramesh");
		student.setId(1L);
		student.setLastName("Ganta");
		student.setPhoneNumber("080-888-7777");
		students.add(student);
		
		return students;
		
	}
	

}
