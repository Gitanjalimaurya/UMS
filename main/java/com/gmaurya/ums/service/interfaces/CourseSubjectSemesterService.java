package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.entity.Subject;

import java.util.List;

public interface CourseSubjectSemesterService {

    List<CourseSubjectSemester> getAllCourseSubjectSemesters();
    boolean addCourseSubjectSemester(CourseSubjectSemester courseSubjectSemester);
    String deleteCourseSubjectSemester(Long id);
    List<Course> getAllCourses();
    List<Subject> getAllSubjects();

    List<CourseSubjectSemesterDto> getSubjectsByCourseId(String courseId);
}
