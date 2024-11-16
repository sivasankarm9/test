package com.sample.ems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Management ",
                version = "1.0",
                description = "Employee Management System will support the following operations <br> <b> 1. Add Employee<br>\n, 2.Update Employee,<br>\n 3.Delete Employee, <br> 4. Fetch Employee <br><b>\n"
        )
)
@Configuration
public class SwaggerConfig {
}
