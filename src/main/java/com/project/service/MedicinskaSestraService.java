package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.MedicinskaSestra;
import com.project.repository.MedicinskaSestraRepository;

@Service
public class MedicinskaSestraService {

	@Autowired
	private MedicinskaSestraRepository medicinskaSestraRepository;
	
	public MedicinskaSestra findById(Long id) {
		return medicinskaSestraRepository.findById(id).orElseGet(null);
	}
}
