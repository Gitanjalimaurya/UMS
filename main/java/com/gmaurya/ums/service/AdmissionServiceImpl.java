package com.gmaurya.ums.service;

import com.gmaurya.ums.service.interfaces.AdmissionService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.gmaurya.ums.dto.AdmissionDto;
import com.gmaurya.ums.entity.Admission;
import com.gmaurya.ums.repository.AdmissionRepository;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdmissionServiceImpl implements AdmissionService {

	private final AdmissionRepository admissionRepository;

	public AdmissionServiceImpl(AdmissionRepository admissionRepository) {
		super();
		this.admissionRepository = admissionRepository;
	}

	@Override
	public void save(AdmissionDto admissionDto) throws IOException {
		byte[] studentImage = null;

		if (admissionDto.getStudent_image() != null && !admissionDto.getStudent_image().isEmpty()) {
			try {
				studentImage = admissionDto.getStudent_image().getBytes();
			} catch (IOException e) {
				System.err.println("Error while reading the uploaded file: " + e.getMessage());
				throw new IOException("Error while processing the uploaded file", e);
			}
		} else {
			System.err.println("No file uploaded or the file is empty.");
			throw new IllegalArgumentException("No file uploaded or the file is empty.");
		}

		byte[] highSchoolMarksheet = null;

		if (admissionDto.getHigh_school_marksheet() != null && !admissionDto.getHigh_school_marksheet().isEmpty()) {
			try {
				highSchoolMarksheet = admissionDto.getHigh_school_marksheet().getBytes();
			} catch (IOException e) {
				System.err.println("Error while reading the uploaded file: " + e.getMessage());
				throw new IOException("Error while processing the uploaded file", e);
			}
		} else {
			throw new IllegalArgumentException("No file uploaded or the file is empty.");
		}

		byte[] intermediateMarksheet = null;

		if (admissionDto.getIntermediate_marksheet() != null && !admissionDto.getIntermediate_marksheet().isEmpty()) {
			try {
				intermediateMarksheet = admissionDto.getIntermediate_marksheet().getBytes();
			} catch (IOException e) {
				System.err.println("Error while reading the uploaded file: " + e.getMessage());
				throw new IOException("Error while processing the uploaded file", e);
			}
		} else {
			throw new IllegalArgumentException("No file uploaded or the file is empty.");
		}

		Admission admission = new Admission(admissionDto.getStudent_name(),
				admissionDto.getFather_name(),
				admissionDto.getMother_name(),
				Date.valueOf(admissionDto.getDate_of_birth()),
				admissionDto.getReligion(),
				admissionDto.getGender(),
				admissionDto.getHouse_no(),
				admissionDto.getStreet_name(),
				admissionDto.getCity(),
				admissionDto.getState(),
				admissionDto.getCountry(),
				admissionDto.getPin_code(),
				studentImage,
				admissionDto.getStudent_phone_no(),
				admissionDto.getParent_phone_no(),
				admissionDto.getStudent_email(),
				admissionDto.getParent_email(),
				admissionDto.getCategory(),
				admissionDto.getHigh_school_board(),
				admissionDto.getHigh_school_year_of_passing(),
				admissionDto.getHigh_school_marks(),
				admissionDto.getIntermediate_board(),
				admissionDto.getIntermediate_year_of_passing(),
				admissionDto.getIntermediate_marks(),
				admissionDto.getDegree(),
				admissionDto.getStream(),
				admissionDto.getCourse(),
				highSchoolMarksheet,
				intermediateMarksheet
				);

		admissionRepository.save(admission);
	}


	// for faculty to view admission
	@Override
	public List<Admission> getAllAdmissions() {
		return admissionRepository.findAll();
	}

	@Override
	public Optional<Admission> getAdmissionById(Long application_form_no) {
		return admissionRepository.findById(application_form_no);
	}

	//for form display of image and pdfs
	@Override
	public Optional<byte[]> getStudentImage(Long application_form_no) {
		return admissionRepository.findById(application_form_no)
				.map(Admission::getStudent_image);
	}

	@Override
	public Optional<byte[]> getHighSchoolMarksheet(Long application_form_no) {
		return admissionRepository.findById(application_form_no)
				.map(Admission::getHigh_school_marksheet);
	}

	@Override
	public Optional<byte[]> getIntermediateMarksheet(Long application_form_no) {
		return admissionRepository.findById(application_form_no)
				.map(Admission::getIntermediate_marksheet);
	}
}
