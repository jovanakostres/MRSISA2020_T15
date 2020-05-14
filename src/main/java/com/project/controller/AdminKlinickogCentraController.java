package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PregledDto;
import com.project.dto.ZahtevZaRegistracijuDto;
import com.project.model.Pacijent;
import com.project.model.Pregled;
import com.project.model.ZahtevZaRegistraciju;
import com.project.service.AdminKlinickogCentraService;
import com.project.service.LekarService;
import com.project.service.ZahtevZaRegistracijuService;

@RestController
@RequestMapping("/api/akc")
public class AdminKlinickogCentraController {

	@Autowired
    AdminKlinickogCentraService akcService;
	
    @Autowired
    ZahtevZaRegistracijuService zahtevZaRegistracijuService;
	
	   @GetMapping(value ="/zahteviZaReg")
	   public Set<ZahtevZaRegistracijuDto> getZahtevi() {
		   Set<ZahtevZaRegistracijuDto> zahtevi = new HashSet<ZahtevZaRegistracijuDto>();
		 
		   for (ZahtevZaRegistraciju zh : zahtevZaRegistracijuService.findAll()) {
			   zahtevi.add(new ZahtevZaRegistracijuDto(zh.getId(),zh.getEmail(),zh.getIme(),zh.getPrezime(),zh.getDatum(),zh.getVreme()));
		}
		   return zahtevi;
	   }
	
}
