package com.cg.sms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DEPARTMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id", "students"}, allowGetters = true)
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "name must not be null and empty")
	@Column(name = "DEPARTMENT_NAME")
	private String name;
	
	@NotBlank(message = "departmentHeadName must not be null and empty")
	@Column(name = "DEPARTMENT_HEAD_NAME")
	private String departmentHeadName;
	
	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}")
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students;
	
}
