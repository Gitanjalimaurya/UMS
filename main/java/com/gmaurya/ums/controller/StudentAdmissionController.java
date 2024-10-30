package com.gmaurya.ums.controller;

import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.entity.Registration;
import com.gmaurya.ums.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gmaurya.ums.dto.AdmissionDto;
import com.gmaurya.ums.service.interfaces.AdmissionService;
import org.springframework.web.bind.support.SessionStatus;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping
@SessionAttributes("admission")
public class StudentAdmissionController {

	private final AdmissionService admissionService;
	private final RegistrationService registrationService;

	@GetMapping("/admission-personal-info")
	public String showPersonalInfoPage(Principal principal, Model model) {
		String email = principal.getName();
		Optional<Admission> existingAdmission = admissionService.findByEmail(email);
		Optional<Registration> registration = registrationService.findByEmail(email);

		if (existingAdmission.isPresent() && existingAdmission.get().is_admission_filled()) {
			model.addAttribute("message", "You have already filled the admission form.");
			return "prospective-student-dashboard";  // Redirect to the dashboard with a message
		}

		AdmissionDto admissionDto = new AdmissionDto();

		// Autofill fields from Registration
		registration.ifPresent(reg -> {
			admissionDto.setStudent_name(reg.getFirst_name() + " " + reg.getMiddle_name() + " " + reg.getLast_name());
			admissionDto.setHouse_no(reg.getHouse_no());
			admissionDto.setStreet_name(reg.getStreet_name());
			admissionDto.setCity(reg.getCity());
			admissionDto.setState(reg.getState());
			admissionDto.setCountry(reg.getCountry());
			admissionDto.setPin_code(reg.getPin_code());
			admissionDto.setStudent_email(email); // Autofill email
			admissionDto.setStudent_phone_no(reg.getPhone_no());
		});

		model.addAttribute("admission", admissionDto);

		return "admission-personal-info";  // Allow form filling if not filled
	}

	@PostMapping("/admission-personal-info")
	public String submitPersonalInfo(@Validated(AdmissionDto.Personal.class) @ModelAttribute("admission") AdmissionDto admissionDto,
									 BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "admission-personal-info";
		}

		// Store email from Principal in the admissionDto
		admissionDto.setStudent_email(principal.getName());

		return "redirect:/admission-contact-info";
	}

	@GetMapping("/admission-contact-info")
	public String showContactInfoPage(Model model) {
		// Retrieve admissionDto from the model
		AdmissionDto admissionDto = (AdmissionDto) model.getAttribute("admission");

		// If admissionDto is null, return an error or redirect to the previous page
		if (admissionDto == null) {
			model.addAttribute("error", "Session expired or invalid state. Please restart the admission process.");
			return "redirect:/student-dashboard"; // Or redirect to a relevant page
		}

		return "admission-contact-info";
	}

	@PostMapping("/admission-contact-info")
	public String submitContactInfo(@Validated(AdmissionDto.Contact.class) @ModelAttribute("admission") AdmissionDto admissionDto,
									BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "admission-contact-info";
		}

		admissionDto.setStudent_email(principal.getName());

		return "redirect:/admission-education-info";
	}

	@GetMapping("/admission-education-info")
	public String showEducationPage(Model model) {
		// Retrieve admissionDto from the model
		AdmissionDto admissionDto = (AdmissionDto) model.getAttribute("admission");

		// If admissionDto is null, initialize it
		if (admissionDto == null) {
			model.addAttribute("error", "Session expired or invalid state. Please restart the admission process.");
			return "redirect:/student-dashboard"; // Or redirect to a relevant page
		}

		return "admission-education-info";
	}

	@PostMapping("/admission-education-info")
	public String submitEducationInfo(@Validated(AdmissionDto.Education.class) @ModelAttribute("admission") AdmissionDto admissionDto,
									  BindingResult result, SessionStatus status, Principal principal) throws IOException {
		if (result.hasErrors()) {
			return "admission-education-info";
		}

		// Debug the files to see if they are received correctly
		if (admissionDto.getHigh_school_marksheet() == null || admissionDto.getHigh_school_marksheet().isEmpty()) {
			System.out.println("High School Marksheet is missing or empty");
		}

		if (admissionDto.getIntermediate_marksheet() == null || admissionDto.getIntermediate_marksheet().isEmpty()) {
			System.out.println("Intermediate Marksheet is missing or empty");
		}

		// Save admission details and mark it as filled
		admissionDto.setStudent_email(principal.getName());

		admissionService.save(admissionDto);

		status.setComplete();
		return "redirect:/admission-personal-info";
	}
}