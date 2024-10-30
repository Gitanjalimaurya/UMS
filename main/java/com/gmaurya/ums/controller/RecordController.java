package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.CourseSubjectSemester;
import com.gmaurya.ums.service.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // Endpoint for fetching all records
    @GetMapping("/record-management")
    public String getAllRecords(Model model) {
        List<CourseSubjectSemester> courseSubjectSemesters = recordService.getAllCourseSubjectSemestersWithStream();
        model.addAttribute("records", courseSubjectSemesters);
        return "record-management";
    }

    // Endpoint for searching records
    @GetMapping("/record-management/search")
    public String searchRecords(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
        List<CourseSubjectSemester> filteredRecords = recordService.searchRecords(searchTerm);
        model.addAttribute("records", filteredRecords);
        return "record-management"; // Return the same page to display the filtered results
    }

    // Endpoint for sorting records
    @GetMapping("/record-management/sort")
    public String sortRecords(@RequestParam(value = "sortBy", required = false) String sortBy, Model model) {
        List<CourseSubjectSemester> sortedRecords = recordService.sortRecords(sortBy);
        model.addAttribute("records", sortedRecords);
        return "record-management"; // Return the same page to display the sorted results
    }
}
