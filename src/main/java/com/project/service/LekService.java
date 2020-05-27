package com.project.service;

import java.util.List;

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

	public List<Lek> findAll() {
		// TODO Auto-generated method stub
		return lekRepository.findAll();
	}

	public boolean existsByNaziv(String nazivLeka) {
		// TODO Auto-generated method stub
		return lekRepository.existsByNaziv(nazivLeka);
	}

	public void save(Lek lek) {
		// TODO Auto-generated method stub
		lekRepository.save(lek);
	}

	public Lek findByNaziv(String imeL) {
		// TODO Auto-generated method stub
		return lekRepository.findByNaziv(imeL);
	}
}
