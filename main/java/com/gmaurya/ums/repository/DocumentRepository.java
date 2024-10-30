package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    List<Document> findBySubjectName(String subjectName);
    List<Document> findBySubjectNameIn(List<String> subjectNames);

    @Query("SELECT d FROM Document d WHERE d.subject_name IN :subjectNames")
    List<Document> findDocumentsBySubjectNames(@Param("subjectNames") List<String> subjectNames);


}
