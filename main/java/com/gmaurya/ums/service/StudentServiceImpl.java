package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.entity.Student;
import com.gmaurya.ums.repository.AdmissionRepository;
import com.gmaurya.ums.repository.StudentRepository;
import com.gmaurya.ums.service.interfaces.StudentService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final AdmissionRepository admissionRepository;
    private final StudentRepository studentRepository;
    private final JavaMailSender mailSender;

    public StudentServiceImpl(AdmissionRepository admissionRepository, StudentRepository studentRepository, JavaMailSender mailSender) {
        this.admissionRepository = admissionRepository;
        this.studentRepository = studentRepository;
        this.mailSender = mailSender;
    }

    @Override
    public String acceptStudent(String email) throws Exception {
        try {
            // Find the admission entry by email
            Admission admission = admissionRepository.findByStudentEmail(email)
                    .orElseThrow(() -> new Exception("Admission not found"));

            // Generate a new student ID (e.g., STU101)
            String newStudentId = generateNewStudentId();

            // Transfer admission data to the student table
            Student student = new Student();
            student.setStudentId(newStudentId);
            student.setStudentName(admission.getStudent_name());
            student.setFatherName(admission.getFather_name());
            student.setMotherName(admission.getMother_name());
            student.setDateOfBirth(admission.getDate_of_birth());
            student.setReligion(admission.getReligion());
            student.setGender(admission.getGender());
            student.setHouseNo(admission.getHouse_no());
            student.setStreetName(admission.getStreet_name());
            student.setCity(admission.getCity());
            student.setState(admission.getState());
            student.setCountry(admission.getCountry());
            student.setPinCode(admission.getPin_code());
            student.setStudentPhoneNo(admission.getStudent_phone_no());
            student.setParentPhoneNo(admission.getParent_phone_no());
            student.setStudentEmail(admission.getStudentEmail());
            student.setParentEmail(admission.getParent_email());
            student.setCategory(admission.getCategory());
            student.setHighSchoolBoard(admission.getHigh_school_board());
            student.setHighSchoolYearOfPassing(admission.getHigh_school_year_of_passing());
            student.setHighSchoolMarks(admission.getHigh_school_marks());
            student.setIntermediateBoard(admission.getIntermediate_board());
            student.setIntermediateYearOfPassing(admission.getIntermediate_year_of_passing());
            student.setIntermediateMarks(admission.getIntermediate_marks());
            student.setDegree(admission.getDegree());
            student.setStream(admission.getStream());
            student.setCourse(admission.getCourse());
            student.setStudentImage(admission.getStudent_image());
            student.setHighSchoolMarksheet(admission.getHigh_school_marksheet());
            student.setIntermediateMarksheet(admission.getIntermediate_marksheet());

            // Save the new student record
            studentRepository.save(student);

            // Send acceptance email
            sendAcceptanceEmail(student);

            return newStudentId;
        } catch (Exception e) {
            // Log the error message
            System.err.println("Error in acceptStudent: " + e.getMessage());
            throw new Exception("Failed to accept student: " + e.getMessage(), e); // Rethrow with additional context
        }
    }


    @Override
    public void rejectStudent(String email) throws Exception {
        // Find the admission entry by email
        Admission admission = admissionRepository.findByStudentEmail(email)
                .orElseThrow(() -> new Exception("Admission not found"));

        admissionRepository.delete(admission);

        // Delete the admission entry from the admission table
        sendRejectionEmail(admission);
    }

    @Override
    public void deleteStudent(String email) throws Exception {
        // Find the admission entry by email
        Admission admission = admissionRepository.findByStudentEmail(email)
                .orElseThrow(() -> new Exception("Admission not found"));

        admissionRepository.delete(admission);
    }

    @Override
    public String generateNewStudentId() {
        Optional<Student> lastStudent = studentRepository.findTopByOrderByStudentIdDesc();
        if (lastStudent.isPresent()) {
            String lastId = lastStudent.get().getStudentId();
            int lastNum = Integer.parseInt(lastId.substring(3));
            return "STU" + (lastNum + 1);
        } else {
            return "STU101"; // First student
        }
    }

    @Override
    public void sendAcceptanceEmail(Student student) {
        String to = student.getStudentEmail();
        String subject = "Your application has been accepted!";
        String text = "Congratulations " + student.getStudentName() +
                ", your application has been accepted. Your student ID is " + student.getStudentId() + ".";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mauryagitanjali666@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    @Override
    public void sendRejectionEmail(Admission admission) {
        String to = admission.getStudentEmail();
        String subject = "Your application has been rejected";
        String text = "Dear " + admission.getStudent_name() + ",\n\n" +
                "We regret to inform you that your application has been rejected.\n" +
                "If you have any questions, please contact us.\n\n" +
                "Best regards,\nYour University Admissions Team";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mauryagitanjali666@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    @Override
    public Student getStudentByStudentEmail(String email) {
        return studentRepository.findByStudentEmail(email);
    }

}
