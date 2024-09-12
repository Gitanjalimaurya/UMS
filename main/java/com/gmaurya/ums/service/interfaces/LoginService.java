package com.gmaurya.ums.service.interfaces;

import java.util.Optional;

import com.gmaurya.ums.dto.LoginDto;
import com.gmaurya.ums.entity.Registration;

public interface LoginService {

	//boolean validateUser(LoginDto loginDto, String captchaAnswer, String captchaExpected);
	//Optional<Registration> findById(String email);

	//boolean validateUser(LoginDto loginDto);

	boolean validateUser(String email, String password);
	String getProfileByEmail(String email);
	Optional<Registration> findByEmail(String email);

}
