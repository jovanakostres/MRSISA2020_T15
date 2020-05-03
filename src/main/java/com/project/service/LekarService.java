package com.project.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Lekar;
import com.project.model.Pacijent;
import com.project.repository.LekarRepository;

@Service
public class LekarService {
	
	@Autowired
	private LekarRepository lekarRepository;
	
	public Set<Pacijent> getPacijentiKlinike(Long id){
		Lekar lekar = lekarRepository.findById(id).orElseGet(null);
		return lekar.getKlinika().getPacijenti();
	}
}
