package com.br.course_api.domain.exception;

import com.br.course_api.domain.dto.ErrorMessageDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler {

    private final MessageSource messageSource;

    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO errors = new ErrorMessageDTO(message, err.getField());
            dto.add(errors);
        });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CourseException.BusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> businessHandlerException(CourseException e) {
        return buildDefaultResponse(e);
    }

    private ResponseEntity<ErrorMessageDTO> buildDefaultResponse(CourseException e) {
        ErrorMessageDTO error = new ErrorMessageDTO(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
