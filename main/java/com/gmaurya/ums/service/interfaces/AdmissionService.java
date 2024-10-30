package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.dto.AdmissionDto;
import com.gmaurya.ums.entity.Admission;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdmissionService {
	void save(AdmissionDto admissionDto) throws IOException;

	//Admission fetching for the faculty
	List<Admission> getAllAdmissions();
	Optional<Admission> getAdmissionById(Long application_form_no);
	Optional<Admission> findByEmail(String email);

	//to retrieve image and pdfs for the page
	Optional<byte[]> getStudentImage(Long application_form_no);
	Optional<byte[]> getHighSchoolMarksheet(Long application_form_no);
	Optional<byte[]> getIntermediateMarksheet(Long application_form_no);

}
