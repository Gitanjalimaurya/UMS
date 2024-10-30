package com.gmaurya.ums.service;

import java.util.Optional;

import com.gmaurya.ums.service.interfaces.ForgotPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.gmaurya.ums.dto.ForgotPasswordDto;
import com.gmaurya.ums.entity.Registration;
import com.gmaurya.ums.repository.RegistrationRepository;

@AllArgsConstructor
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	private final RegistrationRepository registrationRepository;

	@Override
    public boolean validateSecurityQuestions(ForgotPasswordDto forgotPasswordDto) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(forgotPasswordDto.getEmail());
        if (optionalRegistration.isPresent())
        {
            Registration registration = optionalRegistration.get();
            boolean first=registration.getQuestion_1().equals(forgotPasswordDto.getQuestion()) && registration.getAnswer_1().equals(forgotPasswordDto.getAnswer());
            boolean second=registration.getQuestion_2().equals(forgotPasswordDto.getQuestion()) && registration.getAnswer_2().equals(forgotPasswordDto.getAnswer());
            boolean third=registration.getQuestion_3().equals(forgotPasswordDto.getQuestion()) && registration.getAnswer_3().equals(forgotPasswordDto.getAnswer());

            return first || second || third;
        }
        return false;
    }
}
