package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.*;

import java.util.List;

public interface SubjectFacultyRelationService {

    void assignSubjectToFaculty(String subjectId, String facultyId);

    List<SubjectFacultyRelation> getAllSubjectFacultyRelations(String sort, String searchQuery);

    void deleteRelation(Long relationId);

    List<SubjectFacultyRelation> getSubjectsByFacultyEmail(String email);
    List<SubjectFacultyRelation> getSubjectsByFacultyId(String facultyId);




    List<Subject> findSubjectsByFacultyId(String facultyId);

}
