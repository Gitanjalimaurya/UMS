package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String courseId; // varchar
    private String degreeId; // varchar
    private String streamId; // varchar
    private String courseName;

    public CourseDto(String courseId, String courseName) {
    }
}
