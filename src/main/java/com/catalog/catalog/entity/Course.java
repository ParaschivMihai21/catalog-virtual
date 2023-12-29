package com.catalog.catalog.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcourse")
    private Integer courseId;
    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourses;


    public Course() {
    }

    public Course(Integer courseId, String title, List<StudentCourse> studentCourses) {
        this.courseId = courseId;
        this.title = title;
        this.studentCourses = studentCourses;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", studentCourses=" + studentCourses +
                '}';
    }
}
