package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.StudentAssignmentDto;
import com.gmaurya.ums.entity.Assignment;
import com.gmaurya.ums.entity.StudentAssignment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentAssignmentService {

    List<StudentAssignment> findCompletedAssignmentsForStudent(String studentEmail);
    void uploadAssignment(StudentAssignmentDto assignmentDto) throws IOException;
    List<Assignment> findPendingAssignmentsForStudent(String studentEmail);

}
