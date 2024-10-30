package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.FacultyDto;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    Faculty save(FacultyDto facultyDTO);

    List<Faculty> getAllFaculties();

    List<Faculty> deleteFaculty(String facultyId);

    List<Subject> getSubjectsByFacultyEmail(String email);
    List<Document> getDocumentsForFaculty(String email);

    Optional<Faculty> findByEmail(String email);
}
