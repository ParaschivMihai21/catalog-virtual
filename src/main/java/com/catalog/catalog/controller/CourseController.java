package com.catalog.catalog.controller;

import com.catalog.catalog.dto.CourseDTO;
import com.catalog.catalog.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer courseId) throws Exception {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PutMapping
    public void addCourse(@RequestBody CourseDTO courseDTO) throws Exception {
        courseService.addCourse(courseDTO);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Integer courseId) throws Exception {
        return ResponseEntity.ok(courseService.deleteCourseById(courseId));
    }

}
