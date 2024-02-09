package com.br.course_api.infrastructure.entity;

import com.br.course_api.domain.enums.ActiveEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String category;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;

    @CreationTimestamp
    private String created_at;

    @CreationTimestamp
    private String updated_at;
}
