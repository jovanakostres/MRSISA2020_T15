package com.project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.*;
import com.project.repository.LekarRepository;

@Service
public class LekarService {
	
	@Autowired
	private LekarRepository lekarRepository;
	
	public Set<Pacijent> getPacijentiKlinike(Long id){
		Lekar lekar = lekarRepository.findById(id).orElseGet(null);
		return lekar.getKlinika().getPacijenti();
	}
	
	public List<Lekar> filter(String ime, String prez, String broj, String minmax, Long id) {
		if(minmax.equalsIgnoreCase("min"))
			return lekarRepository.filterKorisnikMin(ime, prez, broj, id);
		else
			return lekarRepository.filterKorisnikMax(ime, prez, broj, id);
	}

	public Lekar findById(Long id) {
		return lekarRepository.findById(id).orElseGet(null);
	}
	
	public List<Lekar> search(String param, Long id) {
		// TODO Auto-generated method stub
		return lekarRepository.searchKorisnik(param, id);
	}

}