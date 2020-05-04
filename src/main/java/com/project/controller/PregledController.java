package com.project.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PregledDto;
import com.project.model.Lekar;
import com.project.model.Pregled;
import com.project.service.LekarService;
import com.project.service.PregledService;

@RestController
@RequestMapping("/api/pregled")
public class PregledController {
	
	@Autowired
	PregledService pregledService;
	
	@Autowired
	LekarService lekarService;
	
	@GetMapping(value ="/zakazani")
	   public Set<PregledDto> getLekari() {
		   Lekar l = lekarService.findById(3L);
		   Set <PregledDto> pregledi = new HashSet<PregledDto>();
		   
		   for (Pregled pregled : pregledService.getZakazaniPregledi(l)) {
			   pregledi.add(new PregledDto(pregled.getId(),pregled.getZkPacijenta().getPacijent().getIme(),pregled.getZkPacijenta().getPacijent().getPrezime(),pregled.getSala().getNaziv(),pregled.getDatum(),pregled.getVreme(),pregled.getTrajanjePregleda(),pregled.getCena()));
		}
		   
		   return pregledi;
	   }
}
