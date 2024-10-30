package com.gmaurya.ums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    private String studentId;

    private String studentName;
    private String fatherName;
    private String motherName;

    private Date dateOfBirth;
    private String religion;
    private String gender;

    private String houseNo;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    private String studentPhoneNo;
    private String parentPhoneNo;

    @Column(unique = true)
    private String studentEmail;
    private String parentEmail;

    private String category;

    private String highSchoolBoard;
    private int highSchoolYearOfPassing;
    private float highSchoolMarks;

    private String intermediateBoard;
    private int intermediateYearOfPassing;
    private float intermediateMarks;

    private String degree;
    private String stream;
    private String course;

    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] studentImage;

    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] highSchoolMarksheet;

    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] intermediateMarksheet;

    @Column(columnDefinition = "integer default 1")
    private Integer semester = 1;
}
