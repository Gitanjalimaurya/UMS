package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/document-view")
    public String showDocumentList(Model model){
        List<Document> document = documentService.getAllDocuments();
        model.addAttribute("document",document);
        return "document-view";
    }

    @GetMapping("/document-upload")
    public String showUploadForm(Model model){
        model.addAttribute("document", new DocumentDto());
        return "document-upload";
    }

    @PostMapping("/document-upload")
    public String uploadDocument(@ModelAttribute("document") DocumentDto documentDto,
                                 BindingResult result, Model model) throws IOException {

        if (result.hasErrors()) {
            return "document-upload";
        }

        if (documentDto.getDocument_pdf() == null || documentDto.getDocument_pdf().isEmpty()) {
            System.out.println("Document is missing or empty");
        }

        documentService.save(documentDto);
        return "redirect:/document-view";
    }

    @GetMapping("/document-download/{document_id}")
    public ResponseEntity<byte[]> getDocumentPdf(@PathVariable("document_id") Long document_id) {
        byte[] document_pdf = documentService.getDocumentPdf(document_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Adjust the media type as needed
                .body(document_pdf);
    }

    @PostMapping("/document-delete/{document_id}")
    public String deleteDocument(@PathVariable("document_id") Long document_id) {
        documentService.delete(document_id);
        return "redirect:/document-view";
    }
}
