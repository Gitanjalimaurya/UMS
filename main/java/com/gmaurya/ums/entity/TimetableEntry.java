package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "timetable_entries")
public class TimetableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Day cannot be blank")
    @Size(max = 20, message = "Day cannot exceed 20 characters")
    private String day;

    @NotBlank(message = "Period cannot be blank")
    @Size(max = 20, message = "Period cannot exceed 20 characters")
    private String period;

    @NotBlank(message = "Subject cannot be blank")
    @Size(max = 50, message = "Subject cannot exceed 50 characters")
    private String subject;

    @NotBlank(message = "Teacher cannot be blank")
    @Size(max = 50, message = "Teacher cannot exceed 50 characters")
    private String teacher;

    @NotBlank(message = "Classroom cannot be blank")
    @Size(max = 20, message = "Classroom cannot exceed 20 characters")
    private String classroom;

    @NotNull(message = "Time cannot be null")
    private String time;

    @ManyToOne
    @JoinColumn(name = "class_section_id", nullable = false)
    private ClassSection classSection;

    @Override
    public String toString() {
        return "TimetableEntry{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", period='" + period + '\'' +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classroom='" + classroom + '\'' +
                ", time=" + time +
                ", classSection=" + classSection +
                '}';
    }
}