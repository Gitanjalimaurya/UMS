package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_assignment")
public class StudentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_assignment_id")
    private Long id; // Unique ID for each student assignment

    // Reference to the Assignment entity (facultyId and assignmentName come from Assignment)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignment_no", nullable = false)
    private Assignment assignment; // Assignment uploaded by the faculty (facultyId and assignmentName will be used)

    // Reference to the Student entity (studentId based on student email)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Student submitting the assignment

    @Column(name = "assignment_file", nullable = false, columnDefinition = "BYTEA")
    private byte[] assignmentFile; // PDF file of the student's submission

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate; // Date and time of submission

}
