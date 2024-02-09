package com.br.course_api.domain.mapper;

import com.br.course_api.domain.response.CourseResponse;
import com.br.course_api.infrastructure.entity.CourseEntity;

public class CourseMapper {

    public static CourseResponse ToResponse(CourseEntity entity) {
        return CourseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .active(entity.getActive())
                .build();
    }

}
