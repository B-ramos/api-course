package com.br.course_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCourserDTO(
        @NotBlank String name,
        @NotBlank String category
) {
}
