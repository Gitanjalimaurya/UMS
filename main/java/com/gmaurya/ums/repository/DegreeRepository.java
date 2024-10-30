package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeRepository extends JpaRepository<Degree,String> {

    Optional<Degree> findByDegreeName(String degreeName);
}
