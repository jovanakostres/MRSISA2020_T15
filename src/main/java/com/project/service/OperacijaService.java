package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Operacija;
import com.project.repository.OperacijaRepository;

@Service
public class OperacijaService {

	@Autowired
	OperacijaRepository operacijaRepository;

	public void save(Operacija o) {
		// TODO Auto-generated method stub
		operacijaRepository.save(o);
	}
	
}
