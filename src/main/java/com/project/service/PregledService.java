package com.project.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Lekar;
import com.project.model.Pregled;
import com.project.repository.IzvestajRepository;
import com.project.repository.PregledRepository;

@Service
public class PregledService {

	@Autowired
	private PregledRepository pregledRepository;
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	public Set<Pregled> getZakazaniPregledi(Lekar lekar){
		List<Pregled> pregledi = pregledRepository.findByLekar(lekar);
		Set<Pregled> zpregledi = new HashSet<Pregled>();
		LocalDateTime vr;
		
		for (Pregled pregled : pregledi) {
			if (pregled.getIzvrsen() == false) {
				vr = LocalDateTime.of(pregled.getDatum(),pregled.getVremeOd());
				if(vr.isBefore(LocalDateTime.now())) {
					zpregledi.add(pregled);
				}
			}
		} 
		
		return zpregledi;
	}
	
	public Pregled findById(Long id) {
		return pregledRepository.findById(id).orElseGet(null);
	}
	
	public Pregled save(Pregled pregled) {
		return pregledRepository.save(pregled);
	}

	public List<Pregled> findByDefinisan() {
		// TODO Auto-generated method stub
		return pregledRepository.findByDefinisan(true);
	}

	public Pregled updateByID(Long id) {
		// TODO Auto-generated method stub
		Pregled pregled = pregledRepository.findById(id).orElseGet(null);
		pregled.setDefinisan(false);
		return pregledRepository.save(pregled);
	}

	public List<Pregled> findAll() {
		// TODO Auto-generated method stub
		return pregledRepository.findAll();
	}

	public List<Pregled> findByLekarPacijent(Long id, Long id2) {
		// TODO Auto-generated method stub
		return pregledRepository.findByLekarPacijent(id,id2);
	}
}
