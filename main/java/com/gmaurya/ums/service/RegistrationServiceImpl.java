package com.gmaurya.ums.service;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmaurya.ums.dto.RegistrationDto;
import com.gmaurya.ums.entity.Registration;
import com.gmaurya.ums.repository.RegistrationRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final RegistrationRepository registrationRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
    public Registration save(RegistrationDto registrationDto) {

        Registration registration = new Registration(
            registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()), // Encode the password
			registrationDto.getProfile(),
            registrationDto.getFirst_name(),
            registrationDto.getMiddle_name(),
            registrationDto.getLast_name(),
            registrationDto.getHouse_no(),
            registrationDto.getStreet_name(),
            registrationDto.getCity(),
            registrationDto.getState(),
            registrationDto.getCountry(),
            registrationDto.getPin_code(),
            registrationDto.getPhone_no(),
            registrationDto.getSalutation(),
            registrationDto.getQuestion_1(),
			registrationDto.getAnswer_1(),
            registrationDto.getQuestion_2(),
			registrationDto.getAnswer_2(),
            registrationDto.getQuestion_3(),
			registrationDto.getAnswer_3()
        );
		return registrationRepository.save(registration);
	}

	@Override
	public Optional<Registration> findByEmail(String email) {
		return registrationRepository.findByEmail(email);
	}

	@Override
	public void updateProfileToStudent(String email) {
		registrationRepository.updateProfileByEmail(email, "student");
	}
}
