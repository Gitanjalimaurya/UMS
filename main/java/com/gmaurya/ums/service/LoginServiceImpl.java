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
	
//	@Override
//	public Optional<Registration> findById(String email) {
//		return registrationRepository.findById(email);
//	}

	@Override
	public boolean validateUser(String email, String password) {
		return registrationRepository.findByEmail(email)
				.map(user -> passwordEncoder.matches(password, user.getPassword()))
				.orElse(false);
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

//	@Override
//	public boolean validateUser(LoginDto loginDto) {
//	    Optional<Registration> optionalUser = registrationRepository.findById(loginDto.getEmail());
//
//	    // Check if the user is present
//	    if (optionalUser.isPresent()) {
//	        Registration user = optionalUser.get();
//	        // Validate password
//	        return (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) && (loginDto.getProfile().equals(user.getProfile()));
//	    }
//		return false;
//	}

}
