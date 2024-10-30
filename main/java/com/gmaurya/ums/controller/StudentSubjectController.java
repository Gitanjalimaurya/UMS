package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.CourseSubjectSemesterDto;
import com.gmaurya.ums.entity.Student;
import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.Degree;
import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.service.interfaces.StudentService;
import com.gmaurya.ums.service.interfaces.SubjectService;
import com.gmaurya.ums.service.interfaces.CourseService;
import com.gmaurya.ums.service.interfaces.CourseSubjectSemesterService;
import com.gmaurya.ums.service.interfaces.DegreeService;
import com.gmaurya.ums.service.interfaces.StreamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StudentSubjectController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseSubjectSemesterService courseSubjectSemesterService;
    private final SubjectService subjectService;
    private final DegreeService degreeService;
    private final StreamService streamService;

    public StudentSubjectController(StudentService studentService,
                                    CourseService courseService,
                                    CourseSubjectSemesterService courseSubjectSemesterService,
                                    SubjectService subjectService,
                                    DegreeService degreeService,
                                    StreamService streamService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.courseSubjectSemesterService = courseSubjectSemesterService;
        this.subjectService = subjectService;
        this.degreeService = degreeService;
        this.streamService = streamService;
    }

    @GetMapping("/student-subject")
    public String getStudentSubjects(Model model, Principal principal) {
        String email = principal.getName();
        Student student = studentService.getStudentByStudentEmail(email);

        // Retrieve student's course details
        String courseName = student.getCourse();
        String streamName = student.getStream();
        String degreeName = student.getDegree(); // Assuming you have degree name in the Student entity

        // Step 1: Get Degree
        Degree degree = degreeService.getDegreeByName(degreeName);
        if (degree == null) {
            model.addAttribute("error", "Degree not found for the student.");
            return "student-subject";
        }

        // Step 2: Get Stream
        Stream stream = streamService.getStreamByName(streamName);
        if (stream == null) {
            model.addAttribute("error", "Stream not found for the student.");
            return "student-subject";
        }

        // Step 3: Get Course
        Optional<Course> courseOptional = courseService.getCourseByNameAndDegreeIdAndStreamId(courseName, degree.getDegreeId(), stream.getStreamId());
        if (courseOptional.isEmpty()) {
            model.addAttribute("error", "Course not found for the student.");
            return "student-subject";
        }

        Course course = courseOptional.get();

        // Step 4: Get subjects related to the course ID
        List<CourseSubjectSemesterDto> courseSubjects =
                courseSubjectSemesterService.getSubjectsByCourseId(course.getCourseId());

        // Step 5: Get the subject ID to name map
        Map<String, String> subjectIdToNameMap = subjectService.getSubjectIdToNameMap();

        // Step 6: Add the data to the model
        model.addAttribute("streamName", streamName);
        model.addAttribute("courseName", courseName);
        model.addAttribute("subjects", courseSubjects);
        model.addAttribute("subjectIdToNameMap", subjectIdToNameMap);

        return "student-subject";
    }
}
