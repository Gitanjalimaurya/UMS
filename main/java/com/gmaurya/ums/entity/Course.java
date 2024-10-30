package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "courseId",nullable = false)
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degree; // Reference to Degree entity

    @ManyToOne
    @JoinColumn(name = "streamId")
    private Stream stream; // Reference to Stream entity

    @Column(name = "courseName", nullable = false)
    private String courseName;
}
