package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.IzvestajDto;
import com.project.model.Izvestaj;
import com.project.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	IzvestajRepository izvestajRepository;
	
	public Izvestaj save(Izvestaj izvestaj) {
		return izvestajRepository.save(izvestaj);
	}

	public Izvestaj findById(Long id) {
		// TODO Auto-generated method stub
		return izvestajRepository.findById(id).orElseGet(null);
	}

	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return izvestajRepository.existsById(id);
	}
}
