package com.br.course_api.infrastructure.repository;

import com.br.course_api.infrastructure.entity.CourseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findAll(Specification<CourseEntity> spec);
}
