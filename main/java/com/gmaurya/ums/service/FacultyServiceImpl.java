package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.FacultyDto;
import com.gmaurya.ums.entity.Document;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import com.gmaurya.ums.repository.DocumentRepository;
import com.gmaurya.ums.repository.FacultyRepository;
import com.gmaurya.ums.repository.SubjectFacultyRelationRepository;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import com.gmaurya.ums.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final SubjectFacultyRelationService subjectFacultyRelationService;
    private final SubjectFacultyRelationRepository subjectFacultyRelationRepository;
    private final DocumentRepository documentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, SubjectFacultyRelationService subjectFacultyRelationService, SubjectFacultyRelationRepository subjectFacultyRelationRepository, DocumentRepository documentRepository) {
        this.facultyRepository = facultyRepository;
        this.subjectFacultyRelationService = subjectFacultyRelationService;
        this.subjectFacultyRelationRepository = subjectFacultyRelationRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public Faculty save(FacultyDto facultyDTO) {

        Faculty faculty = new Faculty(
                facultyDTO.getFacultyId(),
                facultyDTO.getName(),
                facultyDTO.getEmail(),
                facultyDTO.getPhoneNo()
        );

        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> deleteFaculty(String facultyId) {

        facultyRepository.deleteById(facultyId);
        return getAllFaculties();
    }

    @Override
    public List<Subject> getSubjectsByFacultyEmail(String email) {
        return subjectFacultyRelationRepository.getSubjectsByFacultyEmail(email);
    }

    @Override
    public List<Document> getDocumentsForFaculty(String email) {
        // Fetch the subject-faculty relations that the faculty teaches
        List<SubjectFacultyRelation> subjectFacultyRelations = subjectFacultyRelationService.getSubjectsByFacultyEmail(email);

        // Extract subject names from the SubjectFacultyRelation objects
        List<String> subjectNames = subjectFacultyRelations.stream()
                .map(relation -> relation.getSubject().getName()) // Assuming getSubject() gives you the Subject object
                .collect(Collectors.toList());

        // Fetch documents related to those subjects
        return documentRepository.findDocumentsBySubjectNames(subjectNames);
    }

    @Override
    public Optional<Faculty> findByEmail(String email) {
        return facultyRepository.findByEmail(email);
    }
}
