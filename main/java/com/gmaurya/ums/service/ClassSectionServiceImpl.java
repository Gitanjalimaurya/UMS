package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.ClassSection;
import com.gmaurya.ums.repository.ClassSectionRepository;
import com.gmaurya.ums.service.interfaces.ClassSectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSectionServiceImpl implements ClassSectionService {

    private final ClassSectionRepository classSectionRepository;

    public ClassSectionServiceImpl(ClassSectionRepository classSectionRepository) {
        this.classSectionRepository = classSectionRepository;
    }

    @Override
    public List<ClassSection> getAllClassSections() {
        return classSectionRepository.findAll();
    }

    @Override
    public ClassSection getClassSectionById(Long id) {
        return classSectionRepository.findById(id).orElse(null);
    }

    @Override
    public ClassSection saveClassSection(ClassSection classSection) {
        return classSectionRepository.save(classSection);
    }

    @Override
    public void deleteClassSection(Long id) {
        classSectionRepository.deleteById(id);
    }

    @Override
    public List<ClassSection> findAll() {
        return classSectionRepository.findAll();
    }
}
