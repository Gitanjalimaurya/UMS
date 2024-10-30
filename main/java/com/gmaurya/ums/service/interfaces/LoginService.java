package com.gmaurya.ums.service.interfaces;

import java.util.Optional;
import com.gmaurya.ums.entity.Registration;

public interface LoginService {

	String getProfileByEmail(String email);
	Optional<Registration> findByEmail(String email);
}
