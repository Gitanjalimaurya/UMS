package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.ClassSection;
import com.gmaurya.ums.service.ClassSectionServiceImpl;
import com.gmaurya.ums.service.interfaces.ClassSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-sections")

public class ClassSectionController {

    private final ClassSectionService classSectionService;

    public ClassSectionController(ClassSectionService classSectionService) {
        this.classSectionService = classSectionService;
    }

    @GetMapping
    public List<ClassSection> getAllClassSections() {
        return classSectionService.getAllClassSections();
    }

    @GetMapping("/{id}")
    public ClassSection getClassSectionById(@PathVariable Long id) {
        return classSectionService.getClassSectionById(id);
    }

    @PostMapping
    public ClassSection createClassSection(@RequestBody ClassSection classSection) {
        return classSectionService.saveClassSection(classSection);
    }

    @DeleteMapping("/{id}")
    public void deleteClassSection(@PathVariable Long id) {
        classSectionService.deleteClassSection(id);
    }
}