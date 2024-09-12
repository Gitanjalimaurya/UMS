package com.gmaurya.ums.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @NotBlank(message = "Document name cannot be blank")
    private String document_name;

    @NotBlank(message = "Course name cannot be blank")
    private String course_name;

    @NotNull(message = "Document cannot be blank")
    private MultipartFile document_pdf;
}
