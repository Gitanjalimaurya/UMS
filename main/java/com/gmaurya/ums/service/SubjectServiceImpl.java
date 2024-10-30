package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.repository.CourseSubjectSemesterRepository;
import com.gmaurya.ums.repository.SubjectRepository;
import com.gmaurya.ums.service.interfaces.SubjectService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final CourseSubjectSemesterRepository courseSubjectSemesterRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, CourseSubjectSemesterRepository courseSubjectSemesterRepository) {
        this.subjectRepository = subjectRepository;
        this.courseSubjectSemesterRepository = courseSubjectSemesterRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name).orElse(null);
    }

    @Override
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public String deleteSubject(String id) {
        // Check if the subject is referenced in the course_subject_semester table
        if (courseSubjectSemesterRepository.existsBySubjectId(id)) {
            return "Warning: This subject is still referenced by one or more courses. Cannot delete.";
        }

        // Proceed with deletion if not referenced
        subjectRepository.deleteById(id);
        return "Subject deleted successfully";
    }

    @Override
    public Map<String, String> getSubjectIdToNameMap() {
        List<Subject> subjects = subjectRepository.findAll();
        Map<String, String> subjectIdToNameMap = new HashMap<>();
        for (Subject subject : subjects) {
            subjectIdToNameMap.put(subject.getId(), subject.getName());
        }
        return subjectIdToNameMap;
    }

}
