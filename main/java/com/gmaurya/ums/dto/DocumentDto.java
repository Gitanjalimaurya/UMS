package com.gmaurya.ums.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @NotBlank(message = "Document name cannot be blank")
    private String document_name;

    @NotBlank(message = "Subject name cannot be blank")
    private String subject_name;

    @NotNull(message = "Document cannot be blank")
    private List<MultipartFile> document_pdfs;
}
