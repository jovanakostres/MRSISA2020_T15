package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Pacijent;
import com.project.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	public Pacijent save(Pacijent pacijent) {
		return pacijentRepository.save(pacijent);
	}
}
