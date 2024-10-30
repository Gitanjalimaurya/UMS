package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.dto.SubjectDto;
import com.gmaurya.ums.entity.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectService {
    List<Subject> getAllSubjects();

    Subject getSubjectById(String id);

    //addded by me
    Subject findByName(String name);

    void saveSubject(Subject subject);

    String deleteSubject(String id);

    Map<String, String> getSubjectIdToNameMap();
}
