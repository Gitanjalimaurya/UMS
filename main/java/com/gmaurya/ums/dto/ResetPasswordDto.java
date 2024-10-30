package com.gmaurya.ums.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {

	@Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String new_password;
}
