package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.service.interfaces.CourseSubjectSemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class CourseSubjectSemesterController {

    private final CourseSubjectSemesterService courseSubjectSemesterService;

    @Autowired
    public CourseSubjectSemesterController(CourseSubjectSemesterService courseSubjectSemesterService) {
        this.courseSubjectSemesterService = courseSubjectSemesterService;
    }

    // View all Course Subject Semesters
    @GetMapping("/view-course-subject-semester")
    public String viewCourseSubjectSemesters(Model model) {
        List<CourseSubjectSemester> courseSubjectSemesters = courseSubjectSemesterService.getAllCourseSubjectSemesters();
        model.addAttribute("courseSubjectSemesters", courseSubjectSemesters);
        return "view-course-subject-semester";
    }

    // Add Course Subject Semester page
    @GetMapping("/add-course-subject-semester")
    public String showAddForm(Model model) {
        model.addAttribute("courseSubjectSemester", new CourseSubjectSemester());
        model.addAttribute("courses", courseSubjectSemesterService.getAllCourses());
        model.addAttribute("subjects", courseSubjectSemesterService.getAllSubjects());
        return "add-course-subject-semester";
    }

    // Handle adding Course Subject Semester
    @PostMapping("/add-course-subject-semester")
    public String addCourseSubjectSemester(@ModelAttribute CourseSubjectSemester courseSubjectSemester, RedirectAttributes redirectAttributes) {
        boolean isAdded = courseSubjectSemesterService.addCourseSubjectSemester(courseSubjectSemester);

        if (isAdded) {
            redirectAttributes.addFlashAttribute("message", "Semester added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("warningMessage", "This Course-Subject combination already exists.");
        }

        return "redirect:/add-course-subject-semester";
    }

    // Delete Course Subject Semester
    @PostMapping("/delete-course-subject-semester/{id}")
    public String deleteCourseSubjectSemester(@PathVariable Long id, Model model) {
        String responseMessage = courseSubjectSemesterService.deleteCourseSubjectSemester(id);
        model.addAttribute("message", responseMessage);

        // Fetch all Course Subject Semesters again to show the updated list
        List<CourseSubjectSemester> courseSubjectSemesters = courseSubjectSemesterService.getAllCourseSubjectSemesters();
        model.addAttribute("courseSubjectSemesters", courseSubjectSemesters);

        // Return to the view with the updated list
        return "view-course-subject-semester"; // Show the same view with the updated entries and message
    }
}
