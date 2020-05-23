package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Recept;
import com.project.model.ZdravstveniKarton;
import com.project.repository.ReceptRepository;
import com.project.repository.ZdrvastveniKartonRepository;

@Service
public class ZdravstveniKartonService {

	@Autowired
	ZdrvastveniKartonRepository zdravstveniKartonRepository;
	
	public ZdravstveniKarton getOne(Long id) {
		return zdravstveniKartonRepository.getOne(id);
	}
	
	public ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton) {
		return zdravstveniKartonRepository.save(zdravstveniKarton);
	}

	public ZdravstveniKarton fingByPacijent(Long id) {
		// TODO Auto-generated method stub
		return zdravstveniKartonRepository.findByPacijentId(id);
	}
}
