
package com.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.*;
import com.project.repository.KlinikaRepository;
import com.project.repository.PacijentRepository;

@Service
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Klinika addKlinika(Klinika k){
		return klinikaRepository.save(k);
	}
	
	public List<Klinika> findAll() {
		return klinikaRepository.findAll();
	}
	
	public List<Klinika> filter(String naziv, String adresa, String opis, String minmax)
	{
		if(minmax.equalsIgnoreCase("min"))
			return klinikaRepository.filterKlinikeMin(naziv, adresa, opis);
		else
			return klinikaRepository.filterKlinikeMax(naziv, adresa, opis);
	}
	
	public List<Klinika> search(String param)
	{
		return klinikaRepository.searchKlinike(param);
	}
	
	public Set<Lekar> getLekari(Long ind)
	{
		return klinikaRepository.getOne(ind).getLekari();
	}
}
