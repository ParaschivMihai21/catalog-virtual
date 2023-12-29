package com.catalog.catalog.service;

import java.util.ArrayList;

import com.catalog.catalog.dto.CourseDTO;
import com.catalog.catalog.dto.StudentDTO;
import com.catalog.catalog.entity.Course;
import com.catalog.catalog.entity.Student;
import com.catalog.catalog.entity.StudentCourse;
import com.catalog.catalog.entity.StudentDetail;
import com.catalog.catalog.exceptions.AppException;
import com.catalog.catalog.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void addStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByFirstNameAndLastName(studentDTO.getLastName(),studentDTO.getFirstName()) ){
            throw new AppException("Student is already in the catalog");
        } else {
            Student student = convertDtoToEntity(studentDTO);
            studentRepository.save(student);
        }
    }

    private Student convertDtoToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentGender(studentDTO.getStudentGender());
        studentDetail.setStudentEmail(studentDTO.getStudentEmail());
        studentDetail.setDate(studentDTO.getDate());
        student.setStudentDetail(studentDetail);

        List<StudentCourse> studentCourses = new ArrayList<>();
        if (studentDTO.getCourses() != null) {
            for (CourseDTO courseDTO : studentDTO.getCourses()) {
                Course course = new Course();
                course.setTitle(courseDTO.getTitle());

                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setCourse(course);
                studentCourse.setGrades(courseDTO.getGrades());
                studentCourse.setStudent(student);
                studentCourses.add(studentCourse);
            }
        }

        student.setStudentCourseList(studentCourses);
        return student;
    }


    public StudentDTO getStudentInfoById(Integer studentId) {
        return studentRepository.findById(studentId)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    public StudentDTO updateStudentGrade(StudentDTO studentDTO) {
        Student studentEntity = convertDtoToEntity(studentDTO);
        Student updatedStudentEntity = studentRepository.save(studentEntity);
        StudentDTO updatedStudentDTO = convertEntityToDto(updatedStudentEntity);

        return updatedStudentDTO;
    }


    public List<StudentDTO> getAllStudentInfo() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> searchStudentByName(String name) {

        List<Student> students = studentRepository.findByFirstNameOrLastName(name, name);
        if (students.isEmpty()) {
            throw new AppException("No students found with the name: " + name);
        }
        return students.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }



    public StudentDTO deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
        return null;
    }

    private StudentDTO convertEntityToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setStudentEmail(student.getStudentDetail().getStudentEmail());
        studentDTO.setStudentGender(student.getStudentDetail().getStudentGender());
        studentDTO.setDate(student.getStudentDetail().getDate());


        List<StudentCourse> studentCourses = student.getStudentCourseList();
        List<CourseDTO> courseDTOs = studentCourses.stream()
                .map(studentCourse -> {
                    CourseDTO courseDTO = new CourseDTO();

                    Course course = studentCourse.getCourse();
                    if (course != null) {
                        courseDTO.setCourseId((course.getCourseId()));
                        courseDTO.setTitle(course.getTitle());
                    }
                    courseDTO.setGrades(studentCourse.getGrades());
                    return courseDTO;
                })
                .collect(Collectors.toList());

        studentDTO.setCourses(courseDTOs);

        return studentDTO;

    }


}
