package com.sample.ems.constants;

public class Constants {

    //Employee validations
    public static final String EMPLOYEE_NAME_VALIDATION_MESSAGE = "name can not be null or empty";
    public static final String EMPLOYEE_EMAIL_VALIDATION_MESSAGE = "email can not be null or empty";
    public static final String EMPLOYEE_SALARY_VALIDATION_MESSAGE = "salary must be 10000 or grater";
    public static final String DEPARTMENT_NAME_VALIDATION_MESSAGE = "department name can not be null or empty";
    public static final String EMPLOYEE_EMAIL_FORMAT_VALIDATION_MESSAGE = "Email format must be 'abc@sample.com'";
    public static final String EMPLOYEE_COMPANY_EMAIL_FORMAT_VALIDATION_MESSAGE = "Email format must be 'xxxx@hcl.com'";
    public static final String EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE = "Already employee exists in system with provided email";

    //Exception Messages
    public static final String EMPLOYEE_NOT_FOUND_ERROR_DESCRIPTION_MESSAGE = "Employee Not Found";
    public static final String VALIDATION_ERROR_DESCRIPTION = "Validation Failed";
    public static final String INTERNAL_SERVER_EXCEPTION_ERROR_DESCRIPTION = "Exception occurred while processing request";

    //Pagination
    public static final  int PAGE_DEFAULT_SIZE = 5;
    public static final  int PAGE_DEFAULT_NUMBER = 0;
}
