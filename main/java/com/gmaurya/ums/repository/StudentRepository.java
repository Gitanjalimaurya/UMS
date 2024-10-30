package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s ORDER BY s.studentId DESC")
    Optional<Student> findTopByOrderByStudentIdDesc();

    Student findByStudentEmail(String email);
}
