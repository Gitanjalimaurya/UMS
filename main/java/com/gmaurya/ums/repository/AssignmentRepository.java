package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByFacultyId(String facultyId);

    // Method name changed to follow Spring Data JPA conventions
    List<Assignment> findByStreamNameAndCourseNameAndSemester(String streamName, String courseName, int semester);
    // Method to find assignment by assignmentNo
    Assignment findByAssignmentNo(Long assignmentNo);
}