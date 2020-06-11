package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ZahtevZaOdmor;
import com.project.repository.ZahtevZaOdmorRepository;

@Service
public class ZahtevZaOdmorService {
	
	@Autowired
	private ZahtevZaOdmorRepository zahtevZaOdmorRepository;
	
	public ZahtevZaOdmor save(ZahtevZaOdmor zahtev) {
		return zahtevZaOdmorRepository.save(zahtev);
	}
}
