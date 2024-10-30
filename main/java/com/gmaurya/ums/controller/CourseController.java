package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.service.interfaces.CourseService;
import com.gmaurya.ums.service.interfaces.DegreeService;
import com.gmaurya.ums.service.interfaces.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CourseController {

    private final CourseService courseService;
    private final DegreeService degreeService;
    private final StreamService streamService;

    @Autowired
    public CourseController(CourseService courseService, DegreeService degreeService, StreamService streamService) {
        this.courseService = courseService;
        this.degreeService = degreeService;
        this.streamService = streamService;
    }

    // View all courses
    @GetMapping("/view-course")
    public String viewCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "view-course";
    }

    // Add course page
    @GetMapping("/add-course")
    public String addCoursePage(Model model) {
        model.addAttribute("degrees", degreeService.getAllDegrees());
        model.addAttribute("streams", streamService.getAllStreams());
        model.addAttribute("course", new Course()); // Empty Course object for the form
        return "add-course";
    }

    // Handle adding course
    @PostMapping("/add-course")
    public String addCourse(@ModelAttribute Course course, Model model) {
        courseService.saveCourse(course);
        model.addAttribute("message", "Course added successfully");
        return "add-course"; // Optionally redirect to the add course page or view page
    }

    // Delete course
    @PostMapping("/delete-course/{courseId}")
    public String deleteCourse(@PathVariable String courseId, Model model) {
        String responseMessage = courseService.deleteCourse(courseId);
        model.addAttribute("message", responseMessage);

        // Fetch all courses again to show the updated list
        model.addAttribute("courses", courseService.getAllCourses());

        // Return the view
        return "view-course"; // Show the same view with the updated courses and message
    }
}
