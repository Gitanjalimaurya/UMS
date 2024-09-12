package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private Long document_id;

    @Column(name = "document_name", nullable = false)
    private String document_name;

//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    private Course course;
//
//    @ManyToOne
//    @JoinColumn(name = "faculty_id")
//    private Faculty faculty;

    @Column(name = "course_name", nullable = false)
    private String course_name;

    @Column(name = "document_pdf", columnDefinition = "BYTEA", nullable = false)
    private byte[] document_pdf;

    public Document(String document_name, String course_name, byte[] document_pdf) {
        this.document_name = document_name;
        this.course_name = course_name;
        this.document_pdf = document_pdf;
    }
}
