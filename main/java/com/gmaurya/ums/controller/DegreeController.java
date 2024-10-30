package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Degree;
import com.gmaurya.ums.service.interfaces.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DegreeController {

    private final DegreeService degreeService;

    @Autowired
    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    // View all degrees
    @GetMapping("/view-degree")
    public String viewAllDegrees(Model model) {
        model.addAttribute("degrees", degreeService.getAllDegrees());
        return "view-degree";
    }

    // Add degree page
    @GetMapping("/add-degree")
    public String addDegreePage(Model model) {
        model.addAttribute("degree", new Degree()); // Empty Degree object for the form
        return "add-degree";
    }

    // Handle adding degree
    @PostMapping("/add-degree")
    public String addDegree(@ModelAttribute Degree degree, Model model) {
        degreeService.saveDegree(degree);
        model.addAttribute("message", "Degree added successfully");
        return "add-degree";
    }

    @PostMapping("/delete-degree/{degreeId}")
    public String deleteDegree(@PathVariable String degreeId, Model model) {
        String responseMessage = degreeService.deleteDegree(degreeId);
        model.addAttribute("message", responseMessage);

        // Fetch all degrees again to show the updated list
        model.addAttribute("degrees", degreeService.getAllDegrees());

        // Return the view
        return "view-degree"; // Show the same view with the updated degrees and message
    }

}
