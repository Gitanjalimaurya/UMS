package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.entity.Student;

public interface StudentService {

    String acceptStudent(String email) throws Exception;
    void rejectStudent(String email) throws Exception;
    void deleteStudent(String email) throws Exception;
    String generateNewStudentId();
    void sendAcceptanceEmail(Student student);
    void sendRejectionEmail(Admission admission);

    Student getStudentByStudentEmail(String email);
}
