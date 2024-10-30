package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.repository.*;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectFacultyRelationServiceImpl implements SubjectFacultyRelationService {

    private final SubjectFacultyRelationRepository subjectFacultyRelationRepository;
    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final StreamRepository streamRepository;

    public SubjectFacultyRelationServiceImpl(SubjectFacultyRelationRepository subjectFacultyRelationRepository,
                                             SubjectRepository subjectRepository, FacultyRepository facultyRepository,
                                             CourseRepository courseRepository, StreamRepository streamRepository) {
        this.subjectFacultyRelationRepository = subjectFacultyRelationRepository;
        this.subjectRepository = subjectRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.streamRepository = streamRepository;
    }


    @Override
    public void assignSubjectToFaculty(String subjectId, String facultyId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);

        if (subject.isPresent() && faculty.isPresent()) {
            SubjectFacultyRelation relation = new SubjectFacultyRelation();
            relation.setSubject(subject.get());
            relation.setFaculty(faculty.get());

            subjectFacultyRelationRepository.save(relation);
        } else {
            throw new RuntimeException("Invalid subject or faculty ID");
        }
    }

    @Override
    public List<SubjectFacultyRelation> getAllSubjectFacultyRelations(String sort, String searchQuery) {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return subjectFacultyRelationRepository.searchRelations(searchQuery);
        }

        if ("faculty".equals(sort)) {
            return subjectFacultyRelationRepository.findAllOrderByFacultyName();
        } else if ("subject".equals(sort)) {
            return subjectFacultyRelationRepository.findAllOrderBySubjectName();
        }

        return subjectFacultyRelationRepository.findAll();
    }

    @Override
    public void deleteRelation(Long relationId) {
        subjectFacultyRelationRepository.deleteById(relationId);
    }

    @Override
    public List<SubjectFacultyRelation> getSubjectsByFacultyEmail(String email) {
        Optional<Faculty> facultyOptional = facultyRepository.findByEmail(email);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            return subjectFacultyRelationRepository.findByFaculty(faculty);
        }
        return List.of(); // Return empty list if faculty not found
    }

    @Override
    public List<SubjectFacultyRelation> getSubjectsByFacultyId(String facultyId) {
        return subjectFacultyRelationRepository.findByFaculty_FacultyId(facultyId);
    }




    @Override
    public List<Subject> findSubjectsByFacultyId(String facultyId) {
        // Fetch the SubjectFacultyRelation based on the faculty ID
        List<SubjectFacultyRelation> subjectRelations = getSubjectsByFacultyId(facultyId);

        // Map the SubjectFacultyRelation to Subject entities
        return subjectRelations.stream()
                .map(subjectRelation -> subjectRelation.getSubject()) // Assuming the relation contains a Subject reference
                .collect(Collectors.toList());
    }

}
