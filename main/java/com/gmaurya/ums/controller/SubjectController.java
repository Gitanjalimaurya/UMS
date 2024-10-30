package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.service.interfaces.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject")
    public String getSubjects(Model model) {
        model.addAttribute("subject", subjectService.getAllSubjects());
        return "subject";
    }

    @GetMapping("/subject/{id}")
    public String getSubject(@PathVariable String id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject != null) {
            model.addAttribute("subject", subject);
            return "subject";
        }
        return "error"; // Or redirect to a 404 page
    }

    // View all subjects
    @GetMapping("/view-subject")
    public String viewAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "view-subject";
    }

    // Add subject page
    @GetMapping("/add-subject")
    public String addSubjectPage(Model model) {
        model.addAttribute("subject", new Subject()); // Empty Subject object for the form
        return "add-subject";
    }

    // Handle adding subject
    @PostMapping("/add-subject")
    public String addSubject(@ModelAttribute Subject subject, Model model) {
        subjectService.saveSubject(subject);
        model.addAttribute("message", "Subject added successfully");
        return "add-subject";
    }

    // Handle subject deletion with POST
    @PostMapping("/delete-subject/{id}")
    public String deleteSubject(@PathVariable String id, Model model) {
        String responseMessage = subjectService.deleteSubject(id);
        model.addAttribute("message", responseMessage);

        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "view-subject"; // Redirect after deletion
    }
}
