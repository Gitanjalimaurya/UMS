package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", nullable = false)
    private Long documentId;

    @Column(name = "document_name", nullable = false)
    private String document_name;

    @Column(name = "subject_name", nullable = false)
    private String subject_name;

    @Column(name = "document_pdf", columnDefinition = "BYTEA", nullable = false)
    private byte[] document_pdf;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
