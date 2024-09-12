package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.DocumentDto;
import com.gmaurya.ums.entity.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {
    void save(DocumentDto documentDto) throws IOException;

    List<Document> getAllDocuments();

    Optional<byte[]> getDocumentPdf(Long document_id);

    void delete(Long document_id);
}
