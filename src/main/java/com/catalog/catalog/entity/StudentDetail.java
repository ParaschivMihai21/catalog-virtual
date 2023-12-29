package com.catalog.catalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
@Table(name = "student_detail")
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int detailId;

    @Column(name = "date_birth")
    private String date;

    @Column(name = "gender")
    private String studentGender;
    @Column(name = "email")
    private String studentEmail;

    @OneToOne(mappedBy = "studentDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;

    public StudentDetail() {
    }

    public StudentDetail(int detailId, String date, String studentGender, String studentEmail, Student student) {
        this.detailId = detailId;
        this.date = date;
        this.studentGender = studentGender;
        this.studentEmail = studentEmail;
        this.student = student;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentDetail{" +
                "detailId=" + detailId +
                ", date=" + date +
                ", studentGender='" + studentGender + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                '}';
    }
}
