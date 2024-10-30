package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.FacultyDto;
import com.gmaurya.ums.entity.Faculty;
import com.gmaurya.ums.service.interfaces.FacultyService; // Import the interface
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class FacultyFormController {

    private final FacultyService facultyService; // Use the interface

    public FacultyFormController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/add-faculty")
    public String showAddFacultyForm(Model model) {
        model.addAttribute("faculty", new FacultyDto());
        return "add-faculty"; // Thymeleaf template name
    }

    @PostMapping("/add-faculty")
    public String addFaculty(@Valid @ModelAttribute("faculty") FacultyDto facultyDto,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            // Return to registration form with error messages
            return "add-faculty";
        }

        // If no errors, save the registration data
        facultyService.save(facultyDto);
        return "redirect:/add-faculty?success";
    }

    @GetMapping("/view-faculty")
    public String viewAllFaculties(Model model) {
        model.addAttribute("faculty", facultyService.getAllFaculties());
        return "view-faculty"; // Thymeleaf template name
    }

    @PostMapping("/delete-faculty/{facultyId}")
    public String deleteFaculty(@PathVariable String facultyId, Model model) {
        List<Faculty> updatedFaculties = facultyService.deleteFaculty(facultyId); // Delete and get updated list
        model.addAttribute("faculty", updatedFaculties); // Add updated list to the model
        return "view-faculty"; // Return to the same view-faculty page
    }
}
