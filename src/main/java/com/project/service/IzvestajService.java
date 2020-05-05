package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Izvestaj;
import com.project.repository.IzvestajRepository;

@Service
public class IzvestajService {

	@Autowired
	IzvestajRepository izvestajRepository;
	
	public Izvestaj save(Izvestaj izvestaj) {
		return izvestajRepository.save(izvestaj);
	}
}
