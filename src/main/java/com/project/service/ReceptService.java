package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Recept;
import com.project.repository.ReceptRepository;

@Service
public class ReceptService {

	@Autowired
	ReceptRepository receptRepository;
	
	public Recept save(Recept recept) {
		return receptRepository.save(recept);
	}
}
