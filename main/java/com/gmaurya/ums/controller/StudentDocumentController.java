package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentDocumentController {

    private final DocumentService documentService;

    public StudentDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/document-view-student")
    public String viewDocumentsForStudents(@RequestParam(value = "subjectName", required = false) String subjectName, Model model) {
        List<Document> documents;
        if (subjectName != null && !subjectName.trim().isEmpty()) {
            documents = documentService.getDocumentsBySubjectName(subjectName);
        } else {
            documents = documentService.getAllDocuments(); // Or handle as needed
        }
        model.addAttribute("documents", documents);
        return "document-view-student";
    }
}
