package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.RegistrationDto;
import com.gmaurya.ums.entity.Registration;

public interface RegistrationService {
	Registration save(RegistrationDto registrationDto);
	
}
