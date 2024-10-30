package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.ClassSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSectionRepository extends JpaRepository<ClassSection, Long> {
    // Custom query methods can be added here if needed
}