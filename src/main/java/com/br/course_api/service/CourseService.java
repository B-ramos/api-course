package com.br.course_api.service;

import com.br.course_api.domain.dto.CreateCourserDTO;
import com.br.course_api.domain.dto.UpdateCourserDTO;
import com.br.course_api.domain.enums.ActiveEnum;
import com.br.course_api.domain.exception.CourseException;
import com.br.course_api.domain.mapper.CourseMapper;
import com.br.course_api.domain.response.CourseResponse;
import com.br.course_api.infrastructure.entity.CourseEntity;
import com.br.course_api.infrastructure.specification.CourseSpecification;
import com.br.course_api.infrastructure.repository.CourseRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseResponse> getAll(String name, String category) {

        var list = courseRepository.findAll(CourseSpecification.withFilters(name, category));
        return list.stream().map(CourseMapper::ToResponse).toList();
    }

    public CourseResponse create(CreateCourserDTO courserDTO) {

        var course = CourseEntity.builder()
                .name(courserDTO.name())
                .category(courserDTO.category())
                .active(ActiveEnum.ACTIVE)
                .build();

        var courseEntity = this.courseRepository.save(course);
        return CourseMapper.ToResponse(courseEntity);
    }


    public void update(String id, UpdateCourserDTO updateCourserDTO) {
        UUID courseId = UUID.fromString(id);
        CourseEntity courseEntity = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException.BusinessException("Course not found", HttpStatus.BAD_REQUEST));

        boolean changes = false;

        if (StringUtils.isNotBlank(updateCourserDTO.name()) && !Objects.equals(courseEntity.getName(), updateCourserDTO.name())) {
            courseEntity.setName(updateCourserDTO.name());
            changes = true;
        }

        if (StringUtils.isNotBlank(updateCourserDTO.category()) && !Objects.equals(courseEntity.getCategory(), updateCourserDTO.category())) {
            courseEntity.setCategory(updateCourserDTO.category());
            changes = true;
        }

        if (changes) {
            this.courseRepository.save(courseEntity);
        }
    }

    public void delete(String id) {
        UUID courseId = UUID.fromString(id);
        CourseEntity courseEntity = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException.BusinessException("Course not found", HttpStatus.BAD_REQUEST));

        courseRepository.delete(courseEntity);
    }

    public void changeActiveStatus(String id) {
        UUID courseId = UUID.fromString(id);
        CourseEntity courseEntity = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException.BusinessException("Course not found", HttpStatus.BAD_REQUEST));

        courseEntity.setActive(courseEntity.getActive().changeStatus());
        courseRepository.save(courseEntity);
    }
}
