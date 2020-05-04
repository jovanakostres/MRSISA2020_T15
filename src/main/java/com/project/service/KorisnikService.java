package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Korisnik;
import com.project.model.Lekar;
import com.project.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository korisnikRepository;

	
	public List<Korisnik> filter(String ime, String prez, String broj, String minmax) {
		if(minmax.equalsIgnoreCase("min"))
			return korisnikRepository.filterKorisnikMin(ime, prez, broj);
		else
			return korisnikRepository.filterKorisnikMax(ime, prez, broj);
	}

	public List<Korisnik> search(String param) {
		// TODO Auto-generated method stub
		return korisnikRepository.searchKorisnik(param);
	}
}
