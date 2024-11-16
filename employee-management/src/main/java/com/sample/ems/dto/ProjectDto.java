package com.sample.ems.dto;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String duration;

    private String status;
}
