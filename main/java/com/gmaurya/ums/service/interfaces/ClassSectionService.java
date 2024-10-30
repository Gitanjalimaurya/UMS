package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.ClassSection;

import java.util.List;

public interface ClassSectionService {

    List<ClassSection> getAllClassSections();
    ClassSection getClassSectionById(Long id);
    ClassSection saveClassSection(ClassSection classSection);
    void deleteClassSection(Long id);
    List<ClassSection> findAll();
}
