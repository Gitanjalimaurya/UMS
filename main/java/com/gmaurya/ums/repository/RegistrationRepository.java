package com.gmaurya.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmaurya.ums.entity.Registration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String>{

    Optional<Registration> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Registration r SET r.profile = :profile WHERE r.email = :email")
    void updateProfileByEmail(String email, String profile);
}
