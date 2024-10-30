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
@Table(name = "course_subject_semester")
public class CourseSubjectSemester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course; // Reference to Course entity

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject; // Reference to Subject entity

    private int semester;
}
