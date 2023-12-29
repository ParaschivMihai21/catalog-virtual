package com.catalog.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;



@Entity

@Table(name="student_course")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_course_id")
    private int studentCourseId;

    @Column(name="grades")
    private Integer grades;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="course_id",referencedColumnName = "idcourse")
    private Course course;

    public StudentCourse() {
    }
    public StudentCourse(int studentCourseId, Integer grades, Student student, Course course) {
        this.studentCourseId = studentCourseId;
        this.grades = grades;
        this.student = student;
        this.course = course;
    }
    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "studentCourseId=" + studentCourseId +
                ", grades=" + grades +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
