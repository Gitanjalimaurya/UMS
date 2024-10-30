package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.RegistrationDto;
import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.entity.Registration;

import java.util.Optional;

public interface RegistrationService {
	Registration save(RegistrationDto registrationDto);
	Optional<Registration> findByEmail(String email);
	void updateProfileToStudent(String email);
}
