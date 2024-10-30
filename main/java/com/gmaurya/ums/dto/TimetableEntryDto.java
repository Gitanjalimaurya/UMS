package com.gmaurya.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimetableEntryDto {

    private Long id;
    private String day;
    private String period;
    private String subject;
    private String teacher;
    private String classroom;
    private String time;
}
