package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DoctorDTO;

public class DoctorForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotNull(message = "Date of birth is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@NotEmpty(message = "Mobile No is required")
	private String mobile;

	@NotEmpty(message = "Expertise is required")
	private String expertise;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@Override
	public BaseDTO getDto() {
		DoctorDTO dto = (DoctorDTO) initDTO(new DoctorDTO());
		dto.setName(name);
		dto.setDob(dob);
		dto.setMobile(mobile);
		dto.setExpertise(expertise);
		return dto;
	}
}