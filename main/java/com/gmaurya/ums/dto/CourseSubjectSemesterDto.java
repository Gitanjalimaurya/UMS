package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubjectSemesterDto {

    private Long id;
    private String courseId; // varchar
    private String subjectId; // varchar
    private int semester;
}
