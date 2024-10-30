package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_no")
    private Long assignmentNo; // Auto-generated assignment number

    @NotBlank(message = "Assignment name cannot be blank")
    @Column(name = "assignment_name", nullable = false)
    private String assignmentName;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "faculty_id", nullable = false)
    private String facultyId; // Store faculty ID as a String

    @Column(name = "stream_name", nullable = false)
    private String streamName; // Store stream name as a String

    @Column(name = "course_name", nullable = false)
    private String courseName; // Store course name as a String

    @Column(name = "subject_name", nullable = false)
    private String subjectName; // Store subject name as a String

    @Column(name = "semester", nullable = false)
    private int semester; // Store semester as a String

}
