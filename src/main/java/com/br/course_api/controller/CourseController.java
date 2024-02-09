package com.br.course_api.controller;

import com.br.course_api.domain.dto.CreateCourserDTO;
import com.br.course_api.domain.dto.UpdateCourserDTO;
import com.br.course_api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public ResponseEntity<Object> getAll(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String category) {

        var response = courseService.getAll(name, category);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourserDTO courserDTO) {

        var response = courseService.create(courserDTO);
        return ResponseEntity
                .created(URI.create("GET/course/" + response.id()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UpdateCourserDTO updateCourserDTO, @PathVariable String id) {

        courseService.update(id, updateCourserDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> toggleActiveStatus(@PathVariable String id) {

        courseService.changeActiveStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
