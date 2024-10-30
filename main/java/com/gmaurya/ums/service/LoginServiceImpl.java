package com.gmaurya.ums.service;

import java.util.Optional;

import com.gmaurya.ums.service.interfaces.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmaurya.ums.dto.LoginDto;
import com.gmaurya.ums.entity.Registration;
import com.gmaurya.ums.repository.RegistrationRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final RegistrationRepository registrationRepository;
	private final PasswordEncoder passwordEncoder;
	
	//Parameterized Constructor
	public LoginServiceImpl(RegistrationRepository registrationRepository, PasswordEncoder passwordEncoder) {
		super();
		this.registrationRepository = registrationRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public String getProfileByEmail(String email) {
		return registrationRepository.findByEmail(email)
				.map(Registration::getProfile)
				.orElse(null);
	}

	@Override
	public Optional<Registration> findByEmail(String email) {
		return registrationRepository.findByEmail(email);
	}

}
