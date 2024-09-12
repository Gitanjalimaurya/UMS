package com.gmaurya.ums.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gmaurya.ums.dto.AdmissionDto;
import com.gmaurya.ums.service.interfaces.AdmissionService;
import org.springframework.web.bind.support.SessionStatus;

import java.io.IOException;

@AllArgsConstructor
@Controller
@RequestMapping
@SessionAttributes("admission")
public class StudentAdmissionController {
	
	private final AdmissionService admissionService;

	@ModelAttribute("admission")
	public AdmissionDto admissionDto(){
		return new AdmissionDto();
	}

	@GetMapping("/admission-personal-info")
	public String showPersonalInfoPage() {
		return "admission-personal-info";
	}

	@PostMapping("/admission-personal-info")
    public String submitPersonalInfo(@Validated(AdmissionDto.Personal.class) @ModelAttribute("admission") AdmissionDto admissionDto,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "admission-personal-info";
        }
        return "redirect:/admission-contact-info";
    }

	@GetMapping("/admission-contact-info")
	public String showContactInfoPage(){
		return "admission-contact-info";
	}

	@PostMapping("/admission-contact-info")
	public String submitContactInfo(@Validated(AdmissionDto.Contact.class) @ModelAttribute("admission") AdmissionDto admissionDto,
									BindingResult result){
		if(result.hasErrors()){
			return "admission-contact-info";
		}
		return "redirect:/admission-education-info";
	}

	@GetMapping("/admission-education-info")
	public String showEducationPage(){
		return "admission-education-info";
	}

	@PostMapping("/admission-education-info")
	public String submitEducationInfo(@Validated(AdmissionDto.Education.class) @ModelAttribute("admission") AdmissionDto admissionDto,
									BindingResult result, SessionStatus status) throws IOException {
		if(result.hasErrors()){
			return "admission-education-info";
		}

		// Debug the files to see if they are received correctly
		if (admissionDto.getHigh_school_marksheet() == null || admissionDto.getHigh_school_marksheet().isEmpty()) {
			System.out.println("High School Marksheet is missing or empty");
		}

		if (admissionDto.getIntermediate_marksheet() == null || admissionDto.getIntermediate_marksheet().isEmpty()) {
			System.out.println("Intermediate Marksheet is missing or empty");
		}

		admissionService.save(admissionDto);
		status.setComplete();
		return "redirect:/success";
	}

	@GetMapping("/success")
	public String showSuccessPage(){
		return "admission-personal-info";
	}

}
