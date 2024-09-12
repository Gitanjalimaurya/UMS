package com.gmaurya.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmaurya.ums.dto.LoginDto;
import com.gmaurya.ums.service.interfaces.LoginService;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class LoginController {

	private LoginService loginService;

	@GetMapping("/login")
	public String showLoginPage(Model model){
		model.addAttribute("loginDto", new LoginDto());
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "login";
		}

		boolean validUser = loginService.validateUser(loginDto.getEmail(), loginDto.getPassword());
		if (validUser) {
			String profile = loginService.getProfileByEmail(loginDto.getEmail());
			return switch (profile) {
				case "student" -> "redirect:/student-dashboard";
				case "faculty" -> "redirect:/faculty-dashboard";
				case "admin" -> "redirect:/admin-dashboard";
				default -> "redirect:/login?error=true";
			};
		}

		return "redirect:/login?error=true";
	}


}

