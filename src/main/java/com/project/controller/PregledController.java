package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.CustomUserDetails;
import com.project.dto.PregledDto;
import com.project.dto.ZKIzvestajDto;
import com.project.dto.ZdravstveniKartonDTO;
import com.project.model.Lekar;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.ZdravstveniKarton;
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
		public Set<PregledDto> getZakazaniPregledi() {
			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Lekar l = lekarService.findById(lekarService.findByEmail(userDetails.getUsername()).getId());
			Set <PregledDto> pregledi = new HashSet<PregledDto>();
			String op;
			for (Pregled pregled : pregledService.getZakazaniPregledi(l)) {
				pregledi.add(new PregledDto(pregled.getId(),pregled.getZkPacijenta().getPacijent().getIme(),pregled.getZkPacijenta().getPacijent().getPrezime(),pregled.getSala().getNaziv(),pregled.getDatum(),pregled.getVremeOd(),pregled.getVremeDo(),pregled.getCena(),pregled.getIzvrsen(),"Pregled"));
			}
		   
		   return pregledi;
		}
	
	@GetMapping(value="/pregledIzvestaj/{id}")
	   public PregledDto getPregled(@PathVariable Long id) {
		   Pregled pr = pregledService.findById(id);
		   String op;
		   PregledDto pregled = new PregledDto(pr.getId(),pr.getZkPacijenta().getPacijent().getIme(),pr.getZkPacijenta().getPacijent().getPrezime(),pr.getSala().getNaziv(),pr.getDatum(),pr.getVremeOd(),pr.getVremeDo(),pr.getCena(),pr.getIzvrsen(),"Pregled");
		   
		   return pregled;
	   }
	
	@RequestMapping(value = "/zdravstveni_karton/{id}", method = RequestMethod.GET)
	   public ZKIzvestajDto getZdravstveniKartonIzvestaj(@PathVariable Long id)
	   {
			Pregled pr = pregledService.findById(id);
		   
		   ZKIzvestajDto zk = new ZKIzvestajDto(pr.getZkPacijenta().getId(),pr.getZkPacijenta().getVisina(),pr.getZkPacijenta().getTezina(),pr.getZkPacijenta().getKrvnaGrupa());
		   
		   return zk;
	   }
}
