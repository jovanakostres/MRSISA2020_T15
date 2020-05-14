package com.project.service;

import java.util.Optional;

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
	
	public Optional<Pacijent> findById(Long id) {
		return pacijentRepository.findById(id);
	}
	
	public Pacijent updateById(Pacijent p) {
		return pacijentRepository.save(p);
	}
	
	public Pacijent findByEmail(String email) {
		return pacijentRepository.findByEmail(email);
	}
}
