package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.repository.DocumentRepository;
import com.gmaurya.ums.service.interfaces.DocumentService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void save(DocumentDto documentDto) throws IOException{

        byte[] document_pdf = null;

        if (documentDto.getDocument_pdf()!= null && !documentDto.getDocument_pdf().isEmpty()) {
            try {
                document_pdf = documentDto.getDocument_pdf().getBytes();
            } catch (IOException e) {
                System.err.println("Error while reading the uploaded file: " + e.getMessage());
                throw new IOException("Error while processing the uploaded file", e);
            }
        } else {
            throw new IllegalArgumentException("No file uploaded or the file is empty.");
        }

        Document document=new Document(documentDto.getDocument_name(),
                documentDto.getCourse_name(),
                document_pdf
        );

        documentRepository.save(document);
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
}
