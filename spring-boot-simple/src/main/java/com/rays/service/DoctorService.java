package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DoctorDAO;
import com.rays.dto.DoctorDTO;

@Service
@Transactional
public class DoctorService {

	@Autowired
	public DoctorDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(DoctorDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DoctorDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			DoctorDTO dto = findById(id);
			dao.delete(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public DoctorDTO findById(long pk) {
		DoctorDTO dto = dao.findByPk(pk);
		return dto;
	}

	public List search(DoctorDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}
}