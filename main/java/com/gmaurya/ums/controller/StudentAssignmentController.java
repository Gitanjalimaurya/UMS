package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.StudentAssignmentDto;
import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.StudentAssignment;
import com.gmaurya.ums.service.interfaces.StudentAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class StudentAssignmentController {

    private final StudentAssignmentService studentAssignmentService;

    public StudentAssignmentController(StudentAssignmentService studentAssignmentService) {
        this.studentAssignmentService = studentAssignmentService;
    }

    @GetMapping("/student-assignment-view")
    public String viewAssignments(Model model, Principal principal) {
        String studentEmail = principal.getName(); // Get the logged-in user's email
        List<Assignment> pendingAssignments = studentAssignmentService.findPendingAssignmentsForStudent(studentEmail);
        List<StudentAssignment> completedAssignments = studentAssignmentService.findCompletedAssignmentsForStudent(studentEmail);

        model.addAttribute("studentEmail", studentEmail);
        model.addAttribute("pendingAssignments", pendingAssignments);
        model.addAttribute("completedAssignments", completedAssignments);

        return "student-assignment-view"; // Thymeleaf template name
    }

    @PostMapping("/upload-assignment")
    public String uploadAssignment(@RequestParam("assignmentNo") Long assignmentNo,
                                   @RequestParam("file") MultipartFile file,
                                   Principal principal) throws IOException {
        // Create a new StudentAssignmentDto object
        StudentAssignmentDto assignmentDto = new StudentAssignmentDto();

        // Set the values from the request
        assignmentDto.setAssignmentNo(assignmentNo);
        assignmentDto.setAssignmentFile(file);
        assignmentDto.setStudentEmail(principal.getName()); // Get the logged-in user's email

        // Call the service method with the DTO
        studentAssignmentService.uploadAssignment(assignmentDto);

        // Redirect to the view assignments page after successful upload
        return "redirect:/student-assignment-view";
    }

}
