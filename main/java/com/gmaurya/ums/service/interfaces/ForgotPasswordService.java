package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.ForgotPasswordDto;

public interface ForgotPasswordService {

	boolean validateSecurityQuestions(ForgotPasswordDto forgotPasswordDto);
}
