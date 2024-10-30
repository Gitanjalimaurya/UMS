package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAssignmentDto {

    private Long assignmentNo; // Assignment number from the Assignment table

    private String studentEmail; // Student email to identify the student from the Student table

    private MultipartFile assignmentFile; // File being uploaded by the student

    private LocalDateTime submissionDate; // Submission date and time

}
