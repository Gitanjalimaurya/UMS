package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.AssignmentDto;
import com.gmaurya.ums.dto.CourseDto;
import com.gmaurya.ums.dto.StreamDto;
import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.Stream;
import com.gmaurya.ums.entity.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssignmentService {

    Assignment saveAssignment(Assignment assignment);
    List<Assignment> getAssignmentsByFacultyId(String facultyId);

}
