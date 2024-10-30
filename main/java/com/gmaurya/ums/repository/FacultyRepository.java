package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {

    Optional<Faculty> findByEmail(String email);
}
