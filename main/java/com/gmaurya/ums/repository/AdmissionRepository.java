package com.gmaurya.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmaurya.ums.entity.Admission;

import java.util.Optional;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
}
