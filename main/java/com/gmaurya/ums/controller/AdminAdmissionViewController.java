package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.service.interfaces.AdmissionService;
import com.gmaurya.ums.service.interfaces.RegistrationService;
import com.gmaurya.ums.service.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping
public class AdminAdmissionViewController {

    private final AdmissionService admissionService;
    private final StudentService studentService;
    private final RegistrationService registrationService;

    @GetMapping("/student-list-admission-admin")
    public String showStudentList(Model model) {
        List<Admission> admission = admissionService.getAllAdmissions();
        model.addAttribute("admissions", admission);
        return "student-list-admission-admin";
    }

    @GetMapping("/students-details-admission-admin/{application_form_no}")
    public String showStudentDetails(@PathVariable("application_form_no") Long application_form_no, Model model) {
        Optional<Admission> admissionOptional = admissionService.getAdmissionById(application_form_no);
        if (admissionOptional.isEmpty()) {
            model.addAttribute("error", "Student not found");
            return "error";
        }

        Admission admission = admissionOptional.get();
        model.addAttribute("admission", admission);
        return "student-details-admission-admin";
    }

    @PostMapping("/student-details-admission-admin/accept")
    public String acceptStudent(@RequestParam("email") String email, Model model) {
        try {
            System.out.println("Received email for acceptance: " + email);

            // Update the profile to 'student' in the registration table
            registrationService.updateProfileToStudent(email);

            //save student details in the student table
            String studentId = studentService.acceptStudent(email);

            //delete student admission details from admission table
            studentService.deleteStudent(email);

            model.addAttribute("successMessage", "Student accepted with ID: " + studentId);
        } catch (Exception e) {
            System.err.println("Failed to accept the application: " + e.getMessage());
            model.addAttribute("errorMessage", "Failed to accept the application: " + e.getMessage());
        }
        return "redirect:/student-list-admission-admin";
    }

    @PostMapping("/student-details-admission-admin/reject")
    public String rejectStudent(@RequestParam("email") String email, Model model) {
        try {
            studentService.rejectStudent(email);
            model.addAttribute("successMessage", "Student application rejected");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/student-list-admission-admin";
    }

    // for image and pdfs
    @GetMapping("/student-image-admin/{application_form_no}")
    public ResponseEntity<byte[]> getStudentImage(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] image = admissionService.getStudentImage(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type as needed
                .body(image);
    }

    @GetMapping("/high-school-marksheet-admin/{application_form_no}")
    public ResponseEntity<byte[]> getHighSchoolMarksheet(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] marksheet = admissionService.getHighSchoolMarksheet(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(marksheet);
    }

    @GetMapping("/intermediate-marksheet-admin/{application_form_no}")
    public ResponseEntity<byte[]> getIntermediateMarksheet(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] marksheet = admissionService.getIntermediateMarksheet(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(marksheet);
    }
}
