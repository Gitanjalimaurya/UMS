package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.service.interfaces.SubjectService;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminDocumentController {

    private final DocumentService documentService;
    private final SubjectService subjectService; // Add course service to retrieve course info

    public AdminDocumentController(DocumentService documentService, SubjectService subjectService) {
        this.documentService = documentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/document-view-admin")
    public String viewDocuments(@RequestParam(value = "name", required = false) String name,
                                Model model) {
        List<Document> documents;
        if (name != null && !name.isEmpty()) {
            documents = documentService.getDocumentsBySubjectName(name);
        } else {
            documents = documentService.getAllDocuments();
        }
        model.addAttribute("document", documents);
        return "/document-view-admin";
    }

    @GetMapping("/document-upload-admin")
    public String showDocumentUploadForm(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects(); // Assuming you have a method to get subjects
        model.addAttribute("subjects", subjects);
        model.addAttribute("document", new DocumentDto());
        return "document-upload-admin"; // Return the name of your Thymeleaf template
    }


    @PostMapping("/document-upload-admin")
    public String uploadDocument(@ModelAttribute("document") DocumentDto documentDto,
                                 BindingResult result, Model model) throws IOException {

        if (result.hasErrors()) {
            return "document-upload-admin";
        }

        if (documentDto.getDocument_pdfs() == null || documentDto.getDocument_pdfs().isEmpty()) {
            System.out.println("Document is missing or empty");
            return "document-upload-admin";
        }

        //Retrieve course by name
        Subject subject = subjectService.findByName(documentDto.getSubject_name());
        if (subject == null) {
            System.out.println("Subject not found");
            return "document-upload-admin"; // Error case if the course is not found
        }

        documentService.save(documentDto,subject.getId());

        return "redirect:/document-view-admin"; // Redirect to admin view
    }

    @GetMapping("/document-download-admin/{document_id}")
    public ResponseEntity<byte[]> getDocumentPdf(@PathVariable("document_id") Long document_id) {
        byte[] document_pdf = documentService.getDocumentPdf(document_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(document_pdf);
    }

    @PostMapping("/document-delete-admin/{document_id}")
    public String deleteDocument(@PathVariable("document_id") Long document_id) {
        documentService.delete(document_id);

        return "redirect:/document-view-admin"; // Redirect to admin view

    }
}
