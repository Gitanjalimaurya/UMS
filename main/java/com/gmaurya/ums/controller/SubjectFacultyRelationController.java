package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import com.gmaurya.ums.service.interfaces.SubjectService;
import com.gmaurya.ums.service.interfaces.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubjectFacultyRelationController {

    private final SubjectService subjectService;
    private final FacultyService facultyService;
    private final SubjectFacultyRelationService subjectFacultyRelationService;

    public SubjectFacultyRelationController(SubjectService subjectService, FacultyService facultyService, SubjectFacultyRelationService subjectFacultyRelationService) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.subjectFacultyRelationService = subjectFacultyRelationService;
    }

    // Display the assign form
    @GetMapping("/assign-subject")
    public String showAssignSubjectPage(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("subject", subjects);
        model.addAttribute("faculties", faculties);
        return "assign-subject"; // assign-subject.html template
    }

    // Handle the assignment of courses to faculty
    @PostMapping("/assign-subject")
    public String assignCourseToFaculty(@RequestParam("subjectId") String subjectId,
                                        @RequestParam("facultyId") String facultyId,
                                        Model model) {
        subjectFacultyRelationService.assignSubjectToFaculty(subjectId, facultyId);
        model.addAttribute("successMessage", "Subject assigned successfully!");
        return "redirect:/assign-subject"; // redirect after successful assignment
    }

    // View all faculties and their assigned courses
    @GetMapping("/view-faculty-subjects")
    public String viewFacultySubjects(
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "search", required = false) String searchQuery,
            Model model) {

        List<SubjectFacultyRelation> relations = subjectFacultyRelationService.getAllSubjectFacultyRelations(sort, searchQuery);
        model.addAttribute("subjectFacultyRelations", relations);
        model.addAttribute("searchQuery", searchQuery);
        return "view-faculty-subjects"; // view-faculty-subjects.html template
    }

    // Method to handle deletion of a faculty-subject relation
    @PostMapping("/delete-relation")
    public String deleteRelation(@RequestParam("relationId") Long relationId, Model model) {
        subjectFacultyRelationService.deleteRelation(relationId);
        model.addAttribute("successMessage", "Relation deleted successfully!");
        return "redirect:/view-faculty-subjects"; // Redirect back to the view page
    }

}

