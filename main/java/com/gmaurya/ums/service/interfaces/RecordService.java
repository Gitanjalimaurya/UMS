package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.*;

import java.util.List;

public interface RecordService {
    List<CourseSubjectSemester> getAllCourseSubjectSemestersWithStream();
    List<CourseSubjectSemester> searchRecords(String searchTerm);
    List<CourseSubjectSemester> sortRecords(String sortBy);
}
