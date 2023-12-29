package com.catalog.catalog.service;
import com.catalog.catalog.dto.CourseDTO;
import com.catalog.catalog.entity.Course;
import com.catalog.catalog.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentCourseService studentCourseService;

    public CourseService(CourseRepository courseRepository, StudentCourseService studentCourseService) {
        this.courseRepository = courseRepository;
        this.studentCourseService = studentCourseService;
    }

    private CourseDTO mapToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setTitle(course.getTitle());
        return courseDTO;
    }

    private Course mapToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
        course.setTitle(courseDTO.getTitle());
        return course;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Integer courseId) throws Exception {
        if (!courseRepository.existsById(courseId)) {
            throw new Exception("Course is not in the list");
        } else {
            return courseRepository.findById(courseId)
                    .map(this::mapToDTO)
                    .orElse(null);
        }
    }

    public void addCourse(CourseDTO courseDTO) throws Exception {
        if (courseRepository.existsById(courseDTO.getCourseId())) {
            throw new Exception("Course is already listed");
        } else {
            Course course = mapToEntity(courseDTO);
            courseRepository.save(course);
        }
    }

    public CourseDTO deleteCourseById(Integer courseId) throws Exception {
        if (!courseRepository.existsById(courseId)) {
            throw new Exception("Course Id " + courseId + " is not in the list");
        } else {
            courseRepository.deleteById(courseId);
            return null;
        }
    }

}