package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.TimetableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntry, Long> {

    // If ClassSection has a relationship with Subject, use this method:
    @Query("SELECT t FROM TimetableEntry t WHERE t.classSection.subject.id = :subjectId AND t.classSection.id = :classSectionId")
    List<TimetableEntry> findBySubjectIdAndClassSectionId(@Param("subjectId") Long subjectId, @Param("classSectionId") Long classSectionId);

    // Find timetable entries by ClassSection ID only:
    @Query("SELECT t FROM TimetableEntry t WHERE t.classSection.id = :classSectionId")
    List<TimetableEntry> findByClassSectionId(@Param("classSectionId") Long classSectionId);



}
