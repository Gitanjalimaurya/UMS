package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.SubjectFacultyRelation;
import com.gmaurya.ums.repository.FacultyRepository;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class FacultySubjectController {

    private final SubjectFacultyRelationService subjectFacultyRelationService;
    private final FacultyRepository facultyRepository;

    public FacultySubjectController(SubjectFacultyRelationService subjectFacultyRelationService, FacultyRepository facultyRepository) {
        this.subjectFacultyRelationService = subjectFacultyRelationService;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("/faculty-subject")
    public String getFacultySubjects(Model model, Principal principal) {
        String email = principal.getName(); // Get the logged-in user's email

        List<SubjectFacultyRelation> subjectFacultyRelations = subjectFacultyRelationService.getSubjectsByFacultyEmail(email);

        // Add the faculty's name (if needed) or subject information to the model
        model.addAttribute("subjectFacultyRelations", subjectFacultyRelations);

        // If you want to display faculty information, retrieve the faculty entity as well
        Faculty faculty = facultyRepository.findByEmail(email).orElse(null);
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
        }

        return "faculty-subject";
    }

}
