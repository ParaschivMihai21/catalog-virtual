package com.catalog.catalog.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private String date;
    private String studentGender;
    private String studentEmail;
    private List<CourseDTO> courses;


}
