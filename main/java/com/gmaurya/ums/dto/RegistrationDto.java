package com.gmaurya.ums.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	
	@Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;

	@NotBlank(message = "Profile cannot be blank")
	private String profile;

	@NotBlank(message = "First name cannot be blank")
	private String first_name;
	
	private String middle_name;
	
	@NotBlank(message = "Last name cannot be blank")
	private String last_name;
	
	@NotBlank(message = "House number/building name is required")
	private String house_no;
	
	@NotBlank(message = "Street name/area/locality is required")
	private String street_name;
	
	@NotBlank(message = "City/town/village is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;
	
	@NotBlank(message = "Country is required")
	private String country;
	
	@NotBlank(message = "Pin-code cannot be blank")
    @Pattern(regexp = "^\\d{6}$", message = "Pin-code must be exactly 6 digits")
	private String pin_code;
	
	@NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone_no;
	
	@NotBlank(message = "Salutation cannot be blank")
	private String salutation;

	@NotBlank(message = "Question 1 cannot be blank")
	private String question_1;

	@NotBlank(message = "Answer cannot be blank")
	private String answer_1;

	@NotBlank(message = "Question 2 cannot be blank")
	private String question_2;

	@NotBlank(message = "Answer cannot be blank")
	private String answer_2;
	
	@NotBlank(message = "Question 3 cannot be blank")
	private String question_3;

	@NotBlank(message = "Answer cannot be blank")
	private String answer_3;
}
