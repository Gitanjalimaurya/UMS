package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.service.interfaces.CourseService;
import com.gmaurya.ums.service.interfaces.DegreeService;
import com.gmaurya.ums.service.interfaces.StreamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProspectiveStudentCourseController {

    private final DegreeService degreeService;
    private final StreamService streamService;
    private final CourseService courseService;

    public ProspectiveStudentCourseController(DegreeService degreeService, StreamService streamService, CourseService courseService) {
        this.degreeService = degreeService;
        this.streamService = streamService;
        this.courseService = courseService;
    }

    @GetMapping("/prospective-student-course")
    public String getProspectiveStudentCourses(
            Model model,
            @RequestParam(required = false) String sort) {

        List<Course> courses = courseService.getAllCourses();

        // Sort courses based on selected criteria
        if ("degreeName".equals(sort)) {
            courses.sort((c1, c2) -> c1.getDegree().getDegreeName().compareTo(c2.getDegree().getDegreeName()));
        } else if ("streamName".equals(sort)) {
            courses.sort((c1, c2) -> c1.getStream().getStreamName().compareTo(c2.getStream().getStreamName()));
        } else if ("courseName".equals(sort)) {
            courses.sort((c1, c2) -> c1.getCourseName().compareTo(c2.getCourseName()));
        } else if ("duration".equals(sort)) {
            courses.sort((c1, c2) -> Integer.compare(c1.getStream().getDuration(), c2.getStream().getDuration())); // Use Integer.compare for int
        }

        model.addAttribute("courses", courses);
        model.addAttribute("sort", sort);
        return "prospective-student-course";
    }
}
