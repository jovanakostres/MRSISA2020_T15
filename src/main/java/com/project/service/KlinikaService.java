package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Klinika;
import com.project.model.Pacijent;
import com.project.repository.KlinikaRepository;
import com.project.repository.PacijentRepository;

@Service
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public List<Klinika> findAll() {
		return klinikaRepository.findAll();
	}
}
