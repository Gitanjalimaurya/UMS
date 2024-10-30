package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFacultyRelationRepository extends JpaRepository<SubjectFacultyRelation, Long> {

    @Query("SELECT r FROM SubjectFacultyRelation r WHERE r.faculty.name LIKE %:searchQuery% OR r.subject.name LIKE %:searchQuery% OR r.subject.id LIKE %:searchQuery% OR r.faculty.facultyId LIKE %:searchQuery%")
    List<SubjectFacultyRelation> searchRelations(String searchQuery);

    @Query("SELECT r FROM SubjectFacultyRelation r ORDER BY r.faculty.name")
    List<SubjectFacultyRelation> findAllOrderByFacultyName();

    @Query("SELECT r FROM SubjectFacultyRelation r ORDER BY r.subject.name")
    List<SubjectFacultyRelation> findAllOrderBySubjectName();

    List<SubjectFacultyRelation> findByFaculty(Faculty faculty);
    List<Subject> getSubjectsByFacultyEmail(String email);

    List<SubjectFacultyRelation> findByFaculty_FacultyId(String facultyId);









    // Find all subjects taught by a specific faculty
    @Query("SELECT s.subject FROM SubjectFacultyRelation s WHERE s.faculty.facultyId = :facultyId")
    List<Subject> findSubjectsByFacultyId(String facultyId);
}
