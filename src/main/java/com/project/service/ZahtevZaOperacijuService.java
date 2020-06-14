package com.project.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ZahtevZaOperaciju;
import com.project.model.ZahtevZaPregled;
import com.project.repository.ZahtevZaOperacijuRepository;
import com.project.repository.ZahtevZaPregledRepository;

@Service
public class ZahtevZaOperacijuService {
	@Autowired
	private ZahtevZaOperacijuRepository zahtevZaOperacijuRepository;
	
	public Set<ZahtevZaOperaciju> findByPraznaSala() {
		// TODO Auto-generated method stub
		return zahtevZaOperacijuRepository.findBySala(null);
	}

	public ZahtevZaOperaciju findById(Long id) {
		// TODO Auto-generated method stub
		return zahtevZaOperacijuRepository.findById(id).orElseGet(null);
	}

	public void delete(ZahtevZaOperaciju zzo) {
		// TODO Auto-generated method stub
		zahtevZaOperacijuRepository.delete(zzo);
	}
	
}
