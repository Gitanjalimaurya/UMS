package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.Student;
import com.gmaurya.ums.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {

    // Find completed assignments by studentId and assignmentNo
    Optional<StudentAssignment> findByStudentStudentIdAndAssignmentAssignmentNo(String studentId, Long assignmentNo);

    boolean existsByStudentStudentIdAndAssignmentAssignmentNo(String studentId, Long assignmentNo);

    // Method to find by student and assignment
    Optional<StudentAssignment> findByStudentAndAssignment(Student student, Assignment assignment);
}
