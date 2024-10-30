package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import com.gmaurya.ums.service.interfaces.FacultyService;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import com.gmaurya.ums.service.interfaces.SubjectService;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FacultyDocumentController {

    private final DocumentService documentService;
    private final SubjectService subjectService; // Add course service to retrieve course info
    private final FacultyService facultyService;
    private final SubjectFacultyRelationService subjectFacultyRelationService;

    public FacultyDocumentController(DocumentService documentService, SubjectService subjectService, FacultyService facultyService, SubjectFacultyRelationService subjectFacultyRelationService) {
        this.documentService = documentService;
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.subjectFacultyRelationService = subjectFacultyRelationService;
    }

    @GetMapping("/document-view")
    public String viewDocumentsForFaculty(Model model, Principal principal) {
        // Get the logged-in user's email
        String email = principal.getName();

        // Step 1: Fetch the faculty using the email
        Optional<Faculty> optionalFaculty = facultyService.findByEmail(email);
        if (optionalFaculty.isEmpty()) {
            model.addAttribute("error", "Faculty not found.");
            return "document-view"; // Return an appropriate view in case of an error
        }

        Faculty faculty = optionalFaculty.get();
        String facultyId = faculty.getFacultyId();

        // Step 2: Get the subject IDs associated with the faculty
        List<SubjectFacultyRelation> subjectFacultyRelations = subjectFacultyRelationService.getSubjectsByFacultyId(facultyId);

        // Step 3: Retrieve subject names from the subject IDs
        List<String> subjectNames = subjectFacultyRelations.stream()
                .map(relation -> relation.getSubject().getName())
                .collect(Collectors.toList());

        // Step 4: Fetch documents based on the subject names
        List<Document> documents = documentService.getDocumentsBySubjectNames(subjectNames);

        // Add the documents to the model to display in the view
        model.addAttribute("documents", documents);

        return "document-view";  // The name of your Thymeleaf template
    }


//    @GetMapping("/document-upload") // Add an endpoint to display the upload form
//    public String showUploadForm(Model model, Principal principal) {
//        populateSubjectsModel(model, principal);
//        return "document-upload"; // The name of your Thymeleaf upload template
//    }

    @GetMapping("/document-upload")
    public String showUploadFormFaculty(Model model, Principal principal) {
        // Fetch faculty email from the Principal object
        String facultyEmail = principal.getName();

        // Get faculty ID based on the email
        Faculty faculty = facultyService.findByEmail(facultyEmail)
                .orElseThrow(() -> new RuntimeException("Faculty not found")); // Handle the case where faculty is not found

        // Get the subject-faculty relations for the faculty
        List<SubjectFacultyRelation> relations = subjectFacultyRelationService.getSubjectsByFacultyId(faculty.getFacultyId());

        // Extract subjects from the relations
        List<Subject> subjects = relations.stream()
                .map(SubjectFacultyRelation::getSubject) // Assuming SubjectFacultyRelation has a getSubject() method
                .distinct() // Ensure distinct subjects in case of multiple relations
                .toList();

        // Add subjects to the model
        model.addAttribute("subjects", subjects);
        model.addAttribute("document", new DocumentDto());

        return "document-upload";
    }

    @PostMapping("/document-upload")
    public String uploadDocumentFaculty(@ModelAttribute("document") DocumentDto documentDto,
                                        BindingResult result, Model model, Principal principal) throws IOException {

        // Validate the uploaded documents
        if (result.hasErrors() || documentDto.getDocument_pdfs() == null || documentDto.getDocument_pdfs().isEmpty()) {
            populateSubjectsModel(model, principal);
            return "document-upload";
        }

        // Log the submitted subject name
        System.out.println("Subject Name Submitted: " + documentDto.getSubject_name());

        // Retrieve subject by name (case-insensitive)
        Subject subject = subjectService.findByName(documentDto.getSubject_name());

        if (subject == null) {
            System.out.println("Subject not found: " + documentDto.getSubject_name());
            populateSubjectsModel(model, principal);
            return "document-upload"; // Error case if the subject is not found
        }

        // Iterate through the uploaded documents and save each one
        for (MultipartFile file : documentDto.getDocument_pdfs()) {
            if (!file.isEmpty()) {
                DocumentDto newDocument = new DocumentDto();
                newDocument.setDocument_name(documentDto.getDocument_name()); // Retain the document name
                newDocument.setSubject_name(documentDto.getSubject_name());
                newDocument.setDocument_pdfs(List.of(file)); // Set the current file

                // Save the document with the associated subject ID
                documentService.save(newDocument, subject.getId());
            }
        }

        return "redirect:/document-view"; // Redirect to document view page
    }


    // Helper method to populate subjects in the model
    private void populateSubjectsModel(Model model, Principal principal) {
        String facultyEmail = principal.getName();
        List<SubjectFacultyRelation> relations = subjectFacultyRelationService.getSubjectsByFacultyEmail(facultyEmail);
        List<Subject> subjects = relations.stream()
                .map(SubjectFacultyRelation::getSubject)
                .distinct()
                .toList();

        model.addAttribute("subjects", subjects);
        model.addAttribute("document", new DocumentDto());
    }

    @GetMapping("/document-download/{document_id}")
    public ResponseEntity<byte[]> getDocumentPdfFaculty(@PathVariable("document_id") Long document_id) {
        byte[] document_pdf = documentService.getDocumentPdf(document_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(document_pdf);
    }

    @PostMapping("/document-delete/{document_id}")
    public String deleteDocumentFaculty(@PathVariable("document_id") Long document_id) {
        documentService.delete(document_id);

        return "redirect:/document-view"; // Redirect to regular view
    }

}
