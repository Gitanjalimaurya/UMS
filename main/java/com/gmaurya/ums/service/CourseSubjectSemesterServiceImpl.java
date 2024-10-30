package com.gmaurya.ums.service;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.repository.CourseRepository;
import com.gmaurya.ums.repository.CourseSubjectSemesterRepository;
import com.gmaurya.ums.repository.SubjectRepository;
import com.gmaurya.ums.service.interfaces.CourseSubjectSemesterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseSubjectSemesterServiceImpl implements CourseSubjectSemesterService {

    private final CourseSubjectSemesterRepository courseSubjectSemesterRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public CourseSubjectSemesterServiceImpl(CourseSubjectSemesterRepository courseSubjectSemesterRepository, CourseRepository courseRepository, SubjectRepository subjectRepository) {
        this.courseSubjectSemesterRepository = courseSubjectSemesterRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<CourseSubjectSemester> getAllCourseSubjectSemesters() {
        return courseSubjectSemesterRepository.findAll();
    }

    @Override
    public boolean addCourseSubjectSemester(CourseSubjectSemester courseSubjectSemester) {
        String courseId = courseSubjectSemester.getCourse().getCourseId();
        String subjectId = courseSubjectSemester.getSubject().getId();

        // Check if the combination of courseId and subjectId already exists
        if (courseSubjectSemesterRepository.existsByCourseIdAndSubjectId(courseId, subjectId)) {
            return false;  // Return false to indicate that the combination already exists
        }

        courseSubjectSemesterRepository.save(courseSubjectSemester);
        return true;  // Return true to indicate successful addition
    }

    @Override
    public String deleteCourseSubjectSemester(Long id) {
        // Check if the Course Subject Semester exists
        if (!courseSubjectSemesterRepository.existsById(id)) {
            throw new NoSuchElementException("Course Subject Semester not found");
        }

        // Proceed with deletion
        courseSubjectSemesterRepository.deleteById(id);
        return "Course Subject Semester deleted successfully";
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll().stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<CourseSubjectSemesterDto> getSubjectsByCourseId(String courseId) {
        // Fetch all subjects related to the given courseId without filtering by semester
        List<CourseSubjectSemester> courseSubjects = courseSubjectSemesterRepository.findByCourse_CourseId(courseId);

        // Map the results to CourseSubjectSemesterDto objects
        return courseSubjects.stream()
                .map(cs -> new CourseSubjectSemesterDto(cs.getId(), cs.getCourse().getCourseId(), cs.getSubject().getId(), cs.getSemester())) // Ensure the semester is still included
                .collect(Collectors.toList());
    }

}
