package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.ClassSection;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.TimetableEntry;
import com.gmaurya.ums.repository.ClassSectionRepository;
import com.gmaurya.ums.repository.SubjectRepository;
import com.gmaurya.ums.repository.TimetableRepository;
import com.gmaurya.ums.service.interfaces.TimetableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final SubjectRepository subjectRepository;
    private final ClassSectionRepository classSectionRepository;

    public TimetableServiceImpl(TimetableRepository timetableRepository, SubjectRepository subjectRepository, ClassSectionRepository classSectionRepository) {
        this.timetableRepository = timetableRepository;
        this.subjectRepository = subjectRepository;
        this.classSectionRepository = classSectionRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<ClassSection> getAllClassSections() {
        return classSectionRepository.findAll();
    }

    @Override
    public List<TimetableEntry> getTimetableForSubjectAndSection(Long subjectId, Long classSectionId) {
        return timetableRepository.findBySubjectIdAndClassSectionId(subjectId, classSectionId);
    }

    @Override
    public List<TimetableEntry> getAllTimetableEntries() {
        return timetableRepository.findAll();
    }

    @Override
    public List<TimetableEntry> getTimetableBySubjectAndClassSection(Long subjectId, Long classSectionId) {
        return timetableRepository.findBySubjectIdAndClassSectionId(subjectId, classSectionId);
    }
}
