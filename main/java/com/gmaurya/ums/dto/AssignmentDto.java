package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDto {

    private Long assignmentNo;  // Auto-generated assignment number
    private String assignmentName;
    private String email;
    private String facultyId;      // Change to Long
    private String streamName;   // Change to streamName
    private String courseName;   // Change to courseName
    private String subjectName;   // Change to subjectName
    private int semester;      // Change to String

}
