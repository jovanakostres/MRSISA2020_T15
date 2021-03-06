package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.Korisnik;
import com.project.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
	
	public Korisnik findByEmailAndLozinka(String email, String lozinka) {
		return korisnikRepository.findByEmailAndLozinka(email, lozinka);
	}

	public Korisnik findByEmail(String username) {
		// TODO Auto-generated method stub
		return korisnikRepository.findByEmail(username);
	}

	public void save(Korisnik p) {
		// TODO Auto-generated method stub
		korisnikRepository.save(p);
	}
}
