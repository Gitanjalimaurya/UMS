package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.repository.SubjectRepository;
import com.gmaurya.ums.repository.DocumentRepository;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final SubjectRepository subjectRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, SubjectRepository subjectRepository) {
        this.documentRepository = documentRepository;
        this.subjectRepository = subjectRepository;
    }

    //added by me
    @Transactional
    @Override
    public void save(DocumentDto documentDto, String subjectId) throws IOException {
        // Fetch the subject from the database
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + subjectId));

        // Iterate through each uploaded document
        for (MultipartFile file : documentDto.getDocument_pdfs()) {
            if (!file.isEmpty()) {
                Document document = new Document();
                document.setDocument_name(documentDto.getDocument_name()); // Retain the document name
                document.setSubject_name(documentDto.getSubject_name());
                document.setDocument_pdf(file.getBytes()); // Get the bytes of the uploaded file

                // Set the subject in the document
                document.setSubject(subject);

                // Save the document in the repository
                documentRepository.save(document);
            }
        }
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Optional<byte[]> getDocumentPdf(Long document_id) {
        return documentRepository.findById(document_id)
                .map(Document::getDocument_pdf);
    }

    @Override
    public void delete(Long document_id) {

        documentRepository.deleteById(document_id);
    }

    @Override
    public List<Document> getDocumentsBySubjectName(String subjectName) {
        return documentRepository.findBySubjectName(subjectName);
    }

    @Override
    public List<Document> getDocumentsBySubjectNames(List<String> subjectNames) {
        return documentRepository.findBySubjectNameIn(subjectNames);
    }
}
