package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.service.interfaces.AdmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping
public class FacultyAdmissionViewController {

    private final AdmissionService admissionService;

    @GetMapping("/student-list-admission")
    public String showStudentList(Model model) {

        List<Admission> admission = admissionService.getAllAdmissions();
        model.addAttribute("admissions", admission);
        return "student-list-admission";
    }

    @GetMapping("/students-details-admission/{application_form_no}")
    public String showStudentDetails(@PathVariable("application_form_no") Long application_form_no, Model model) {

        Optional<Admission> admissionOptional = admissionService.getAdmissionById(application_form_no);

        if (admissionOptional.isEmpty()) {
            model.addAttribute("error", "Student not found");
            return "error";
        }

        Admission admission=admissionOptional.get();
        model.addAttribute("admission", admission);
        return "student-details-admission";
    }


    //for image and pdfs
    @GetMapping("/student-image/{application_form_no}")
    public ResponseEntity<byte[]> getStudentImage(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] image = admissionService.getStudentImage(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type as needed
                .body(image);
    }

    @GetMapping("/high-school-marksheet/{application_form_no}")
    public ResponseEntity<byte[]> getHighSchoolMarksheet(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] marksheet = admissionService.getHighSchoolMarksheet(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(marksheet);
    }

    @GetMapping("/intermediate-marksheet/{application_form_no}")
    public ResponseEntity<byte[]> getIntermediateMarksheet(@PathVariable("application_form_no") Long applicationFormNo) {
        byte[] marksheet = admissionService.getIntermediateMarksheet(applicationFormNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(marksheet);
    }

}
