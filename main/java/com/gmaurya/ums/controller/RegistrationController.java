package com.gmaurya.ums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gmaurya.ums.dto.RegistrationDto;
import com.gmaurya.ums.service.interfaces.RegistrationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("registration",new RegistrationDto());
		return "registration";
	}
	
	@PostMapping
    public String registerAccount(@Valid @ModelAttribute("registration") RegistrationDto registrationDto, 
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Return to registration form with error messages
            return "registration";
        }
        
        // If no errors, save the registration data
        registrationService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
