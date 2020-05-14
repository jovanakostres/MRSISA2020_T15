package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ZahtevZaRegistraciju;
import com.project.repository.ZahtevZaRegistracijuRepository;

@Service
public class ZahtevZaRegistracijuService {

	@Autowired
	private ZahtevZaRegistracijuRepository zahtevZaRegistracijuRepository;
	
	public ZahtevZaRegistraciju save(ZahtevZaRegistraciju zahtev) {
		return zahtevZaRegistracijuRepository.save(zahtev);
	}
	
	public void delete(ZahtevZaRegistraciju zahtev) {
		zahtevZaRegistracijuRepository.delete(zahtev);
	}
	
	public List<ZahtevZaRegistraciju> findAll() {
		return zahtevZaRegistracijuRepository.findAll();
	}

	public ZahtevZaRegistraciju findById(Long id) {
		// TODO Auto-generated method stub
		return zahtevZaRegistracijuRepository.findById(id).orElseGet(null);
	}
}
