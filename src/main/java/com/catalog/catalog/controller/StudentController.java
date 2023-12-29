package com.catalog.catalog.controller;

import com.catalog.catalog.dto.StudentDTO;
import com.catalog.catalog.exceptions.AppException;
import com.catalog.catalog.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAllStudentInfo();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getStudentInfoById(studentId));

    }


@DeleteMapping("/{studentId}")
@ResponseBody
public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
   studentService.deleteStudentById(studentId);

        return ResponseEntity.ok().build();
    }


    @PutMapping
    public void addStudent(@RequestBody StudentDTO studentDTO) throws AppException {
        studentService.addStudent(studentDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudentGrade(studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<List<StudentDTO>> searchStudentByName(@PathVariable String name) throws AppException {
        return ResponseEntity.ok(studentService.searchStudentByName(name));
    }
}

