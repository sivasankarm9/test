package com.sample.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
    @SequenceGenerator(name="emp_generator", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize=1)
    @Column(name = "EMP_ID")
    private Long id;
    private String name;
    private String email;
    @Column(name = "EMP_SALARY")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToOne(cascade = CascadeType.ALL)
    private Department department;

}
