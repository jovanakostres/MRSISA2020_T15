package com.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ZahtevZaPregled;
import com.project.repository.ZahtevZaPregledRepository;

@Service
public class ZahtevZaPregledService {
	@Autowired
	private ZahtevZaPregledRepository zahtevZaPregledRepository;
	
	public ZahtevZaPregled save(ZahtevZaPregled zahtev) {
		return zahtevZaPregledRepository.save(zahtev);
	}
	
	public void delete(ZahtevZaPregled zahtev) {
		zahtevZaPregledRepository.delete(zahtev);
	}
	
	public List<ZahtevZaPregled> findAll() {
		return zahtevZaPregledRepository.findAll();
	}

	public ZahtevZaPregled findById(Long id) {
		// TODO Auto-generated method stub
		return zahtevZaPregledRepository.findById(id).orElseGet(null);
	}

	public Set<ZahtevZaPregled> findBySala() {
		// TODO Auto-generated method stub
		return zahtevZaPregledRepository.findBySala(null);
	}
}
