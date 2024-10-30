package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourses();

    void saveCourse(Course course);

    String deleteCourse(String courseId);

    Optional<Course> getCourseByNameAndDegreeIdAndStreamId(String courseName, String degreeId, String streamId);

}