package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.StudentAssignmentDto;
import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.Student;
import com.gmaurya.ums.entity.StudentAssignment;
import com.gmaurya.ums.repository.AssignmentRepository;
import com.gmaurya.ums.repository.StudentAssignmentRepository;
import com.gmaurya.ums.repository.StudentRepository;
import com.gmaurya.ums.service.interfaces.StudentAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentAssignmentServiceImpl implements StudentAssignmentService {

    private final StudentAssignmentRepository studentAssignmentRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;

    public StudentAssignmentServiceImpl(StudentAssignmentRepository studentAssignmentRepository, StudentRepository studentRepository, AssignmentRepository assignmentRepository) {
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<StudentAssignment> findCompletedAssignmentsForStudent(String studentEmail) {
        // Retrieve the student entity using the email
        Student student = studentRepository.findByStudentEmail(studentEmail);
        if (student == null) {
            return new ArrayList<>(); // Return an empty list if the student is not found
        }

        // Fetch all assignments associated with the student's stream, course, and semester
        List<Assignment> assignments = assignmentRepository.findByStreamNameAndCourseNameAndSemester(
                student.getStream(),
                student.getCourse(),
                student.getSemester()
        );

        // Create a list to store completed assignments
        List<StudentAssignment> completedAssignments = new ArrayList<>();

        // Loop through all assignments and check if any have been completed
        for (Assignment assignment : assignments) {
            Optional<StudentAssignment> completedAssignment = studentAssignmentRepository.findByStudentStudentIdAndAssignmentAssignmentNo(
                    student.getStudentId(), assignment.getAssignmentNo());

            // If an assignment is found as completed, add it to the completedAssignments list
            completedAssignment.ifPresent(completedAssignments::add);
        }

        return completedAssignments;
    }


    @Override
    public void uploadAssignment(StudentAssignmentDto assignmentDto) throws IOException {

        // Retrieve the student entity using the email from the DTO
        Student student = studentRepository.findByStudentEmail(assignmentDto.getStudentEmail());
        if (student == null) {
            throw new IllegalArgumentException("Student with email " + assignmentDto.getStudentEmail() + " not found.");
        }

        // Retrieve the assignment entity using assignmentNo from the DTO
        Assignment assignment = assignmentRepository.findByAssignmentNo(assignmentDto.getAssignmentNo());
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment with assignmentNo " + assignmentDto.getAssignmentNo() + " not found.");
        }

        // Check if the student has already submitted the assignment
        Optional<StudentAssignment> existingAssignment = studentAssignmentRepository.findByStudentAndAssignment(student, assignment);
        if (existingAssignment.isPresent()) {
            throw new IllegalArgumentException("Assignment has already been submitted by the student.");
        }

        byte[] assignmentFile = null;

        // Handle the assignment file upload
        if (assignmentDto.getAssignmentFile() != null && !assignmentDto.getAssignmentFile().isEmpty()) {
            try {
                assignmentFile = assignmentDto.getAssignmentFile().getBytes();
            } catch (IOException e) {
                System.err.println("Error while reading the uploaded file: " + e.getMessage());
                throw new IOException("Error while processing the uploaded file", e);
            }
        } else {
            throw new IllegalArgumentException("No file uploaded or the file is empty.");
        }

        // Create and save StudentAssignment entity
        StudentAssignment studentAssignment = new StudentAssignment();
        studentAssignment.setAssignment(assignment);
        studentAssignment.setStudent(student);
        studentAssignment.setSubmissionDate(LocalDateTime.now());
        studentAssignment.setAssignmentFile(assignmentFile);

        studentAssignmentRepository.save(studentAssignment);
    }

    @Override
    public List<Assignment> findPendingAssignmentsForStudent(String studentEmail) {
        // Fetch the student entity using the provided email
        Student student = studentRepository.findByStudentEmail(studentEmail);

        // Check if the student exists
        if (student == null) {
            return new ArrayList<>(); // Return empty list if student is not found
        }

        // Fetch assignments based on the student's stream, course name, and semester
        List<Assignment> assignments = assignmentRepository.findByStreamNameAndCourseNameAndSemester(
                student.getStream(),
                student.getCourse(), // Use the course name directly
                student.getSemester() // Keep semester as an int
        );

        // Filter out assignments that have already been submitted
        List<Assignment> pendingAssignments = new ArrayList<>();
        for (Assignment assignment : assignments) {
            boolean alreadySubmitted = studentAssignmentRepository.existsByStudentStudentIdAndAssignmentAssignmentNo(
                    student.getStudentId(),
                    assignment.getAssignmentNo()
            );
            if (!alreadySubmitted) {
                pendingAssignments.add(assignment); // Add only pending assignments
            }
        }

        return pendingAssignments; // Return the list of pending assignments
    }

}
