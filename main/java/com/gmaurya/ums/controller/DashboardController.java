package com.gmaurya.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/student-dashboard")
    public String showStudentDashboard(){
        return "student-dashboard";
    }

    @GetMapping("/faculty-dashboard")
    public String showFacultyDashboard(){
        return "faculty-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String showAdminDashboard(){
        return "admin-dashboard";
    }
}
