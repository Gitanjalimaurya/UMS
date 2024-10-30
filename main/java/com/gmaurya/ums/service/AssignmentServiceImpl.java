package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.Course;
import com.gmaurya.ums.entity.Student;
import com.gmaurya.ums.repository.*;
import com.gmaurya.ums.service.interfaces.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;
    private final CourseRepository courseRepository;


    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 StudentRepository studentRepository,
                                 StudentAssignmentRepository studentAssignmentRepository,
                                 CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAssignmentsByFacultyId(String facultyId) {
        return assignmentRepository.findByFacultyId(facultyId);
    }

}
