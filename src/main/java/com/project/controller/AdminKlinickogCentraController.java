package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.DijagnozaDto;
import com.project.dto.PregledDto;
import com.project.dto.SifrarnikDto;
import com.project.dto.ZahtevZaRegistracijuDto;
import com.project.model.Dijagnoza;
import com.project.model.Lek;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.ZahtevZaRegistraciju;
import com.project.service.AdminKlinickogCentraService;
import com.project.service.DijagnozaService;
import com.project.service.LekService;
import com.project.service.LekarService;
import com.project.service.ZahtevZaRegistracijuService;

@RestController
@RequestMapping("/api/akc")
public class AdminKlinickogCentraController {

	@Autowired
    AdminKlinickogCentraService akcService;
	
    @Autowired
    ZahtevZaRegistracijuService zahtevZaRegistracijuService;
	
    @Autowired
    DijagnozaService dijagnozaService;
    
    @Autowired
    LekService lekService;
    
	   @GetMapping(value ="/zahteviZaReg")
	   public Set<ZahtevZaRegistracijuDto> getZahtevi() {
		   Set<ZahtevZaRegistracijuDto> zahtevi = new HashSet<ZahtevZaRegistracijuDto>();
		 
		   for (ZahtevZaRegistraciju zh : zahtevZaRegistracijuService.findAll()) {
			   zahtevi.add(new ZahtevZaRegistracijuDto(zh.getId(),zh.getEmail(),zh.getIme(),zh.getPrezime(),zh.getDatum(),zh.getVreme()));
		}
		   return zahtevi;
	   }
	   
	   @GetMapping(value ="/sifrarnik")
	   public Set<SifrarnikDto> getSifrarnik() {
		   Set<SifrarnikDto> sifrarnik = new HashSet<SifrarnikDto>();
		 
		   for (Dijagnoza d : dijagnozaService.findAll()) {
			   for(Lek l : d.getLekovi()) {
				   sifrarnik.add(new SifrarnikDto(l.getId(),d.getId(),l.getNaziv(),d.getNaziv()));
			   }
		}
		   return sifrarnik;
	   }
	   
	   @PostMapping(value ="/dodaj_u_sifarnik")
	   public SifrarnikDto addToSifrarnik(@RequestBody SifrarnikDto sif) {
		   try{
			   Dijagnoza d = dijagnozaService.findByNaziv(sif.getImeD());
			   Lek l = lekService.findByNaziv(sif.getImeL());
				if(d.getLekovi().contains(l))
					return null;
				
				d.getLekovi().add(l);
				dijagnozaService.save(d);
				return new SifrarnikDto(l.getId(),d.getId(),l.getNaziv(),d.getNaziv());
			}
			catch(Exception ex)
			{
				System.out.println(ex);
				return null;
			}
	   }
	
}
