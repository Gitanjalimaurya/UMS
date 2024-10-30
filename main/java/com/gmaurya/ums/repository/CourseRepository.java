package com.gmaurya.ums.repository;

import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.Degree;
import com.gmaurya.ums.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    boolean existsByDegreeDegreeId(String degreeId);

    boolean existsByStreamStreamId(String streamId);

    boolean existsById(String courseId);

    Optional<Course> findByCourseNameAndDegreeAndStream(String courseName, Degree degree, Stream stream);








    Course findByCourseName(String courseName);
}