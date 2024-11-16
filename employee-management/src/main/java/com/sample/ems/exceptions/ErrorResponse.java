package com.sample.ems.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponse {
    private String message;
    private String description;
    private LocalDateTime timeStamp;
    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
}
