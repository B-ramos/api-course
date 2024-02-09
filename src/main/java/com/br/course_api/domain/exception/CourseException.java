package com.br.course_api.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CourseException extends RuntimeException {

    public String message;
    public HttpStatus status;

    public CourseException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static class BusinessException extends CourseException {
        public BusinessException(String msg, HttpStatus status) {
            super(msg, status);
        }
    }
}

