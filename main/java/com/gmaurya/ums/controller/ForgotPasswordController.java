package com.gmaurya.ums.controller;

import com.gmaurya.ums.dto.ForgotPasswordDto;
import com.gmaurya.ums.service.interfaces.ForgotPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

	@GetMapping
    public String showForgotPasswordPage(Model model) {
    	model.addAttribute("forgotPasswordDto",new ForgotPasswordDto());
        return "forgot-password";
    }

    @PostMapping
    public String handleForgotPassword(@ModelAttribute ForgotPasswordDto forgotPasswordDto, Model model) {
        boolean isValid = forgotPasswordService.validateSecurityQuestions(forgotPasswordDto);
        if (isValid) {
            return "redirect:/reset-password?email=" + forgotPasswordDto.getEmail();
        } else {
            model.addAttribute("error", "Invalid email or security answers");
            return "forgot-password";
        }
    }
}

