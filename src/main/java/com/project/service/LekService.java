package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Lek;
import com.project.repository.LekRepository;

@Service
public class LekService {

	@Autowired
	LekRepository lekRepository;
	
	public Lek findById(Long id) {
		return lekRepository.findById(id).orElseGet(null);
	}
}
