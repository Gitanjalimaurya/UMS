package com.gmaurya.ums.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionDto {

    //Validation group interfaces
    public interface Personal{}
    public interface Contact{}
    public interface Education{}

    @NotBlank(message = "Student name cannot be blank", groups = Personal.class)
    private String student_name;
    
    @NotBlank(message = "Father name cannot be blank", groups = Personal.class)
    private String father_name;

    @NotBlank(message = "Mother name cannot be blank", groups = Personal.class)
    private String mother_name;

    @NotNull(message = "Date of Birth cannot be blank", groups = Personal.class)
    @PastOrPresent(message = "Date of Birth must be in the past or present")
    private LocalDate date_of_birth;
    
    @NotBlank(message = "Religion cannot be blank", groups = Personal.class)
	private String religion;

    @NotBlank(message = "Gender cannot be blank", groups = Personal.class)
    private String gender;

    @NotBlank(message = "House number cannot be blank", groups = Personal.class)
    private String house_no;

    @NotBlank(message = "Street name cannot be blank", groups = Personal.class)
    private String street_name;

    @NotBlank(message = "City cannot be blank", groups = Personal.class)
    private String city;

    @NotBlank(message = "State cannot be blank", groups = Personal.class)
    private String state;

    @NotBlank(message = "Country cannot be blank", groups = Personal.class)
    private String country;

    @NotBlank(message = "Pin-code cannot be blank", groups = Personal.class)
    @Pattern(regexp = "^\\d{6}$", message = "Pin-code must be exactly 6 digits")
    private String pin_code;

    @NotNull(message = "Image cannot be blank", groups = Personal.class)
    private MultipartFile student_image;

    @NotBlank(message = "Student phone number cannot be blank", groups = Contact.class)
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String student_phone_no;
    
    @NotBlank(message = "Parent phone number cannot be blank",groups = Contact.class)
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String parent_phone_no;
    
    @NotBlank(message = "Student email cannot be blank", groups = Contact.class)
    @Email(message = "Student email should be valid")
    private String student_email;
    
    @NotBlank(message = "Parent email cannot be blank", groups = Contact.class)
    @Email(message = "Parent email should be valid")
    private String parent_email;
    
    @NotBlank(message = "Category cannot be blank", groups = Personal.class)
    private String category;
    
    @NotBlank(message = "High school board cannot be blank", groups = Education.class)
    private String high_school_board;
    
    @NotNull(message = "High school year of passing cannot be null", groups = Education.class)
    private int high_school_year_of_passing;
    
    @NotNull(message = "High school marks cannot be null", groups = Education.class)
    private float high_school_marks; 
    
    @NotBlank(message = "Intermediate board cannot be blank", groups = Education.class)
    private String intermediate_board;
    
    @NotNull(message = "Intermediate year of passing cannot be null", groups = Education.class)
    private int intermediate_year_of_passing;
    
    @NotNull(message = "Intermediate marks cannot be null", groups = Education.class)
    private float intermediate_marks;

    @NotBlank(message = "Degree cannot be blank", groups = Education.class)
    private String degree;

    @NotBlank(message = "Stream cannot be blank", groups = Education.class)
    private String stream;

    @NotBlank(message = "Subject cannot be blank", groups = Education.class)
    private String course;

    @NotNull(message = "High School marksheet cannot be blank", groups = Education.class)
    private MultipartFile high_school_marksheet;

    @NotNull(message = "Intermediate marksheet cannot be blank", groups = Education.class)
    private MultipartFile intermediate_marksheet;
}
