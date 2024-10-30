package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.service.interfaces.AssignmentService;
import com.gmaurya.ums.service.interfaces.SubjectFacultyRelationService;
import com.gmaurya.ums.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class FacultyAssignmentController {

    private final AssignmentService assignmentService;
    private final SubjectFacultyRelationService subjectFacultyRelationService;
    private final FacultyService facultyService;

    @Autowired
    public FacultyAssignmentController(AssignmentService assignmentService,
                                       SubjectFacultyRelationService subjectFacultyRelationService,
                                       FacultyService facultyService) {
        this.assignmentService = assignmentService;
        this.subjectFacultyRelationService = subjectFacultyRelationService;
        this.facultyService = facultyService;
    }

    @GetMapping("/faculty-assignment-view")
    public String viewAssignments(Model model, Principal principal) {
        String email = principal.getName(); // Get email from Principal

        // Retrieve faculty based on the email
        Optional<Faculty> optionalFaculty = facultyService.findByEmail(email);

        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            String facultyId = faculty.getFacultyId(); // Get the faculty ID

            // Now retrieve assignments based on the faculty ID
            List<Assignment> assignments = assignmentService.getAssignmentsByFacultyId(facultyId);
            model.addAttribute("assignments", assignments);
        } else {
            // Handle the case where the faculty is not found
            model.addAttribute("message", "Faculty not found for the given email.");
        }

        return "faculty-assignment-view"; // This should correspond to the Thymeleaf template name
    }




    // GET mapping to display assignment upload form
    @GetMapping("/faculty-assignment-upload")
    public String showAssignmentUploadForm(Model model,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        Optional<Faculty> optionalFaculty = facultyService.findByEmail(email);

        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            String facultyId = faculty.getFacultyId();
            model.addAttribute("facultyId", facultyId);
            model.addAttribute("subjects", subjectFacultyRelationService.findSubjectsByFacultyId(facultyId));
        }

        model.addAttribute("assignment", new Assignment()); // Create a new Assignment object for the form
        return "faculty-assignment-upload"; // Return the name of the view template (e.g., Thymeleaf template)
    }

    @PostMapping("/faculty-assignment-upload")
    public String uploadAssignment(@ModelAttribute Assignment assignment,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        // Get the faculty ID from the logged-in user
        String email = userDetails.getUsername();
        Optional<Faculty> optionalFaculty = facultyService.findByEmail(email);

        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            String facultyId = faculty.getFacultyId();

            // Set the facultyId to the assignment (only do this once)
            assignment.setFacultyId(facultyId);

            // Save the assignment with the user-entered email
            assignmentService.saveAssignment(assignment);

            // Optionally add a success message or redirect to another page
            model.addAttribute("message", "Assignment uploaded successfully!");
            return "redirect:/faculty-assignment-upload"; // Change this to your success page
        } else {
            model.addAttribute("message", "Faculty not found for the given email.");
            return "error"; // Change this to your error page
        }
    }






    @ModelAttribute("subjects")
    public List<Subject> getSubjects(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        Optional<Faculty> optionalFaculty = facultyService.findByEmail(email);

        if (optionalFaculty.isPresent()) {
            String facultyId = optionalFaculty.get().getFacultyId();
            return subjectFacultyRelationService.findSubjectsByFacultyId(facultyId);
        }

        return List.of(); // Return an empty list if faculty not found
    }
}
