package com.catalog.catalog.controller;

import com.catalog.catalog.dto.StudentCourseDTO;
import com.catalog.catalog.service.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping( "/api/enrollment")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }


    @PostMapping
    public ResponseEntity<?> enrollStudentInCourse(@RequestBody StudentCourseDTO studentCourseDTO) throws Exception {
        studentCourseService.assignCourseToStudent(studentCourseDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> newGrades(@RequestBody StudentCourseDTO studentCourseDTO) {
        studentCourseService.updateStudentGrades(studentCourseDTO);
        return ResponseEntity.ok("Student grades updated");
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<StudentCourseDTO>> getStudentsEnrolledInCourse(@PathVariable int courseId) {
        List<StudentCourseDTO> studentsEnrolled = studentCourseService.getStudentsEnrolledInCourse(courseId);
        return ResponseEntity.ok(studentsEnrolled);
    }
}
