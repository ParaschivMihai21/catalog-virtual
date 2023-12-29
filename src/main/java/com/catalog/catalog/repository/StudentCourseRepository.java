package com.catalog.catalog.repository;

import com.catalog.catalog.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    Optional<StudentCourse> findByStudent_StudentIdAndCourse_CourseId(Integer studentId, Integer courseId);
    List<StudentCourse> findByCourse_CourseId(Integer courseId);

}
