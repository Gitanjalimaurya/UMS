package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.Degree;
import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.repository.CourseRepository;
import com.gmaurya.ums.repository.CourseSubjectSemesterRepository;
import com.gmaurya.ums.repository.DegreeRepository;
import com.gmaurya.ums.repository.StreamRepository;
import com.gmaurya.ums.service.interfaces.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseSubjectSemesterRepository courseSubjectSemesterRepository;
    private final DegreeRepository degreeRepository;
    private final StreamRepository streamRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseSubjectSemesterRepository courseSubjectSemesterRepository, DegreeRepository degreeRepository, StreamRepository streamRepository) {
        this.courseRepository = courseRepository;
        this.courseSubjectSemesterRepository = courseSubjectSemesterRepository;
        this.degreeRepository = degreeRepository;
        this.streamRepository = streamRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public String deleteCourse(String courseId) {
        // Check if the course is referenced in the course_subject_semester table
        if (courseSubjectSemesterRepository.existsByCourseCourseId(courseId)) {
            return "Warning: This course is still referenced by one or more course subject semesters. Cannot delete.";
        }

        // Proceed with deletion if not referenced
        courseRepository.deleteById(courseId);
        return "Course deleted successfully";
    }

    @Override
    public Optional<Course> getCourseByNameAndDegreeIdAndStreamId(String courseName, String degreeId, String streamId) {
        Degree degree = degreeRepository.findById(degreeId).orElseThrow(() -> new EntityNotFoundException("Degree not found"));
        Stream stream = streamRepository.findById(streamId).orElseThrow(() -> new EntityNotFoundException("Stream not found"));

        return courseRepository.findByCourseNameAndDegreeAndStream(courseName, degree, stream);
    }

}
