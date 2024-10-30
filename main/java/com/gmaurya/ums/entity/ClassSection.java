package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString(exclude = "timetableEntries")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class_sections")
public class ClassSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Year cannot be blank")
    @Size(max = 10, message = "Year cannot exceed 10 characters")
    @Column(name = "year", nullable = false, length = 10)
    private String year;

    @NotBlank(message = "Semester cannot be blank")
    @Size(max = 10, message = "Semester cannot exceed 10 characters")
    @Column(name = "semester", nullable = false, length = 10)
    private String semester;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "classSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimetableEntry> timetableEntries;
}
