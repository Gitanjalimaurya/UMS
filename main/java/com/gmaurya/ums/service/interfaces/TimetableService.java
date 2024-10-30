package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.ClassSection;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.TimetableEntry;

import java.util.List;

public interface TimetableService {

    List<Subject> getAllSubjects();
    List<ClassSection> getAllClassSections();
    List<TimetableEntry> getTimetableForSubjectAndSection(Long subjectId, Long classSectionId);
    List<TimetableEntry> getAllTimetableEntries();
    List<TimetableEntry> getTimetableBySubjectAndClassSection(Long subjectId, Long classSectionId);
}
