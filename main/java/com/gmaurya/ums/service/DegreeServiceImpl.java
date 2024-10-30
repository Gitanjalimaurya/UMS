package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Degree;
import com.gmaurya.ums.repository.DegreeRepository;
import com.gmaurya.ums.repository.CourseRepository; // Ensure this is imported
import com.gmaurya.ums.service.interfaces.DegreeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepository degreeRepository;
    private final CourseRepository courseRepository; // Inject the CourseRepository

    public DegreeServiceImpl(DegreeRepository degreeRepository, CourseRepository courseRepository) {
        this.degreeRepository = degreeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    @Override
    public void saveDegree(Degree degree) {
        degreeRepository.save(degree);
    }

    @Override
    public String deleteDegree(String degreeId) {
        // Check if the degree is referenced in the course table
        if (courseRepository.existsByDegreeDegreeId(degreeId)) {
            return "Warning: This degree is still referenced by one or more courses. Cannot delete.";
        }

        // Proceed with deletion if not referenced
        degreeRepository.deleteById(degreeId);
        return "Degree deleted successfully";
    }

    @Override
    public Degree getDegreeByName(String degreeName) {
        Optional<Degree> degreeOptional = degreeRepository.findByDegreeName(degreeName);
        return degreeOptional.orElse(null);
    }
}
