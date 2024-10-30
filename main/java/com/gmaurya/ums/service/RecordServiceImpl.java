package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.repository.CourseSubjectSemesterRepository;
import com.gmaurya.ums.service.interfaces.RecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    private final CourseSubjectSemesterRepository courseSubjectSemesterRepository;

    public RecordServiceImpl(CourseSubjectSemesterRepository courseSubjectSemesterRepository) {
        this.courseSubjectSemesterRepository = courseSubjectSemesterRepository;
    }

    @Override
    public List<CourseSubjectSemester> getAllCourseSubjectSemestersWithStream() {
        return courseSubjectSemesterRepository.findAllWithStream();
    }

    // Search functionality
    @Override
    public List<CourseSubjectSemester> searchRecords(String searchTerm) {
        List<CourseSubjectSemester> allRecords = courseSubjectSemesterRepository.findAllWithStream();
        if (searchTerm == null || searchTerm.isEmpty()) {
            return allRecords;
        }

        return allRecords.stream()
                .filter(record ->
                        (record.getCourse().getDegree().getDegreeName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                record.getCourse().getStream().getStreamName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                record.getCourse().getCourseName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                record.getSubject().getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                String.valueOf(record.getSemester()).contains(searchTerm))
                )
                .collect(Collectors.toList());
    }

    // Sort functionality
    @Override
    public List<CourseSubjectSemester> sortRecords(String sortBy) {
        List<CourseSubjectSemester> allRecords = courseSubjectSemesterRepository.findAllWithStream();

        if (sortBy == null || sortBy.isEmpty()) {
            return allRecords;
        }

        switch (sortBy) {
            case "degree":
                allRecords.sort((a, b) -> a.getCourse().getDegree().getDegreeName().compareToIgnoreCase(b.getCourse().getDegree().getDegreeName()));
                break;
            case "course":
                allRecords.sort((a, b) -> a.getCourse().getCourseName().compareToIgnoreCase(b.getCourse().getCourseName()));
                break;
            case "stream":
                allRecords.sort((a, b) -> a.getCourse().getStream().getStreamName().compareToIgnoreCase(b.getCourse().getStream().getStreamName()));
                break;
            case "subject":
                allRecords.sort((a, b) -> a.getSubject().getName().compareToIgnoreCase(b.getSubject().getName()));
                break;
            case "semester":
                allRecords.sort((a, b) -> Integer.compare(a.getSemester(), b.getSemester()));
                break;
            default:
                break;
        }

        return allRecords;
    }
}
