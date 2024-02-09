package com.br.course_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {
    private String message;

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    public ErrorMessageDTO(String message) {
        this.message = message;
    }
}


