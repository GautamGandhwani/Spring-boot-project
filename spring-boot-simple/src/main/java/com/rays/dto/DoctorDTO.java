package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestMapping;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_DOCTOR")
public class DoctorDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "MOBILE", length = 50)
	private String mobile;

	@Column(name = "EXPERTISE", length = 50)
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
	public String getValue() {
		return null;
	}
}