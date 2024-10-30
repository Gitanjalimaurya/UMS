package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Registration;
import com.gmaurya.ums.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class DashboardController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/student-dashboard")
    public String showStudentDashboard(Principal principal, Model model) {
        String email = principal.getName();
        Optional<Registration> userOptional = registrationRepository.findByEmail(email);
        userOptional.ifPresent(user -> model.addAttribute("studentName", user.getFirst_name()+" "+user.getMiddle_name()+" "+user.getLast_name()));
        return "student-dashboard";
    }

    @GetMapping("/faculty-dashboard")
    public String showFacultyDashboard(Principal principal, Model model) {
        String email = principal.getName();
        Optional<Registration> userOptional = registrationRepository.findByEmail(email);
        userOptional.ifPresent(user -> model.addAttribute("facultyName", user.getFirst_name()+" "+user.getMiddle_name()+" "+user.getLast_name()));
        return "faculty-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String showAdminDashboard(Principal principal, Model model) {
        String email = principal.getName();
        Optional<Registration> userOptional = registrationRepository.findByEmail(email);
        userOptional.ifPresent(user -> model.addAttribute("adminName", user.getFirst_name()+" "+user.getMiddle_name()+" "+user.getLast_name()));
        return "admin-dashboard";
    }

    @GetMapping("/prospective-student-dashboard")
    public String showProspectiveStudentDashboard(Principal principal, Model model) {
        String email = principal.getName();
        Optional<Registration> userOptional = registrationRepository.findByEmail(email);
        userOptional.ifPresent(user -> model.addAttribute("ProspectiveStudentName", user.getFirst_name()+" "+user.getMiddle_name()+" "+user.getLast_name()));
        return "prospective-student-dashboard";
    }
}
