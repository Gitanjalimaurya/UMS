package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.ClassSection;
import com.gmaurya.ums.entity.Subject;
import com.gmaurya.ums.entity.TimetableEntry;
import com.gmaurya.ums.service.interfaces.ClassSectionService;
import com.gmaurya.ums.service.interfaces.SubjectService;
import com.gmaurya.ums.service.interfaces.TimetableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TimetableController {

    private final SubjectService subjectService;
    private final ClassSectionService classSectionService;
    private final TimetableService timetableService;

    public TimetableController(SubjectService subjectService, TimetableService timetableService, ClassSectionService classSectionService) {
        this.subjectService = subjectService;
        this.timetableService = timetableService;
        this.classSectionService = classSectionService;
    }

    @GetMapping("/timetable")
    public String showTimetable(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects(); // Updated method call
        List<ClassSection> classSections = classSectionService.findAll(); // Updated method call
        model.addAttribute("subject", subjects);
        model.addAttribute("classSections", classSections);
        return "timetable";
    }

    @GetMapping("/view-timetable")
    public String viewTimetable(@RequestParam("subject") Long subjectId,
                                @RequestParam("classSection") Long classSectionId,
                                Model model) {
        List<TimetableEntry> timetable = timetableService.getTimetableBySubjectAndClassSection(subjectId, classSectionId);
        model.addAttribute("timetable", timetable);
        return "view-timetable";
    }
}