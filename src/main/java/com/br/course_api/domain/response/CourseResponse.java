package com.br.course_api.domain.response;

import com.br.course_api.domain.enums.ActiveEnum;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseResponse(
        UUID id,
        String name,
        String category,
        ActiveEnum active
) {
}
