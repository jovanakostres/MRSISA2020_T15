package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Dijagnoza;
import com.project.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;

	public List<Dijagnoza> findAll() {
		// TODO Auto-generated method stub
		return dijagnozaRepository.findAll();
	}

	public Dijagnoza findById(Long id) {
		// TODO Auto-generated method stub
		return dijagnozaRepository.findById(id).orElseGet(null);
	}

	public void save(Dijagnoza dijagnoza) {
		dijagnozaRepository.save(dijagnoza);
	}

	public boolean existsByNaziv(String naziv) {
		// TODO Auto-generated method stub
		return dijagnozaRepository.existsByNaziv(naziv);
	}

	public Dijagnoza findByNaziv(String imeD) {
		// TODO Auto-generated method stub
		return dijagnozaRepository.findByNaziv(imeD);
	}
}
