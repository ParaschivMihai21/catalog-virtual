package com.catalog.catalog.dto;

import lombok.Data;

@Data

public class StudentCourseDTO {
    private int studentId;
    private int courseId;
    private Integer grades;
    private String firstName;
    private String lastName;


}
