package com.br.course_api.infrastructure.specification;

import com.br.course_api.infrastructure.entity.CourseEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {
    public static Specification<CourseEntity> withFilters(String name, String category) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.upper(root.get("name")), name.toUpperCase()));
            }

            if (category != null && !category.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(criteriaBuilder.upper(root.get("category")), category.toUpperCase()));
            }

            return predicate;
        };
    }
}

