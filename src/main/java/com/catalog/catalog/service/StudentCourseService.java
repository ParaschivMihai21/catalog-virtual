package com.catalog.catalog.service;

import com.catalog.catalog.dto.StudentCourseDTO;
import com.catalog.catalog.entity.Course;
import com.catalog.catalog.entity.Student;
import com.catalog.catalog.entity.StudentCourse;
import com.catalog.catalog.repository.CourseRepository;
import com.catalog.catalog.repository.StudentCourseRepository;
import com.catalog.catalog.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository,
                                StudentRepository studentRepository,
                                CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public void assignCourseToStudent(StudentCourseDTO studentCourseDTO) throws Exception {
        System.out.println("Received Student ID:--------------------------- " + studentCourseDTO.getStudentId());
        System.out.println("Received Course ID: " + studentCourseDTO.getCourseId());
        Optional<StudentCourse> existingEnrollment = studentCourseRepository
                .findByStudent_StudentIdAndCourse_CourseId(studentCourseDTO.getStudentId(), studentCourseDTO.getCourseId());

        if (existingEnrollment.isPresent()) {
            throw new Exception("Student is already enrolled in this course");
        }
        Student student = studentRepository.findById(studentCourseDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(studentCourseDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        StudentCourse assignCourse = new StudentCourse();
        assignCourse.setCourse(course);
        assignCourse.setStudent(student);
        assignCourse.setGrades(studentCourseDTO.getGrades());
        studentCourseRepository.save(assignCourse);
    }


    public void updateStudentGrades(StudentCourseDTO studentCourseDTO) {
        System.out.println("-------------- " + studentCourseDTO.getStudentId());
        System.out.println("-------------- " + studentCourseDTO.getCourseId());
        StudentCourse existingStudentCourse = studentCourseRepository.findByStudent_StudentIdAndCourse_CourseId(
                studentCourseDTO.getStudentId(),
                studentCourseDTO.getCourseId()

        ).orElseThrow(() -> new RuntimeException("Enrollment not found"));

        existingStudentCourse.setGrades(studentCourseDTO.getGrades());
        studentCourseRepository.save(existingStudentCourse);
    }

    public List<StudentCourseDTO> getStudentsEnrolledInCourse(int courseId) {
        List<StudentCourse> studentCourses = studentCourseRepository.findByCourse_CourseId(courseId);
        return studentCourses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private StudentCourseDTO convertToDTO(StudentCourse studentCourse) {
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setStudentId(studentCourse.getStudent().getStudentId());
        dto.setCourseId(studentCourse.getCourse().getCourseId());
        dto.setGrades(studentCourse.getGrades());
        dto.setFirstName(studentCourse.getStudent().getFirstName());
        dto.setLastName(studentCourse.getStudent().getLastName());
        return dto;
    }

}
