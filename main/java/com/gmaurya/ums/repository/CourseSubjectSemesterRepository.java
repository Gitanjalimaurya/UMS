package com.gmaurya.ums.repository;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSubjectSemesterRepository extends JpaRepository<CourseSubjectSemester, Long> {

    @Query("SELECT c FROM CourseSubjectSemester c JOIN c.course crs JOIN crs.stream str")
    List<CourseSubjectSemester> findAllWithStream();

    boolean existsBySubjectId(String id);
    boolean existsByCourseCourseId(String courseId);

    boolean existsById(Long id);

    @Query("SELECT COUNT(c) > 0 FROM CourseSubjectSemester c WHERE c.course.courseId = ?1 AND c.subject.id = ?2")
    boolean existsByCourseIdAndSubjectId(String courseId, String id);

    List<CourseSubjectSemester> findByCourse_CourseId(String courseId);

}




