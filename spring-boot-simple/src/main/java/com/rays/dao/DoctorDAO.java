package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.DoctorDTO;

@Repository
public class DoctorDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public Long add(DoctorDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(DoctorDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(DoctorDTO dto) {
		entityManager.remove(dto);
	}

	public DoctorDTO findByPk(long pk) {
		DoctorDTO dto = entityManager.find(DoctorDTO.class, pk);
		return dto;
	}

	public List search(DoctorDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DoctorDTO> cq = builder.createQuery(DoctorDTO.class);
		
		Root<DoctorDTO> qRoot = cq.from(DoctorDTO.class);
		
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getName() != null && dto.getName().length() > 0) {
				predicatesList.add(builder.equal(qRoot.get("name"), dto.getName() + "%"));
			}
		}
		cq.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		
		TypedQuery<DoctorDTO> tq=entityManager.createQuery(cq);
		
		if (pageSize >0) {
			tq.setFirstResult(pageNo);
			tq.setMaxResults(pageSize);
		}
		List<DoctorDTO> list =tq.getResultList();
		
		return list;
	}
}