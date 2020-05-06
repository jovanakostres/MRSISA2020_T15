package com.project.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.IzvestajDto;
import com.project.model.Dijagnoza;
import com.project.model.Izvestaj;
import com.project.model.Lek;
import com.project.model.MedicinskaSestra;
import com.project.model.Pregled;
import com.project.model.Recept;
import com.project.model.ZdravstveniKarton;
import com.project.service.DijagnozaService;
import com.project.service.IzvestajService;
import com.project.service.MedicinskaSestraService;
import com.project.service.PregledService;
import com.project.service.ReceptService;
import com.project.service.ZdravstveniKartonService;
import com.project.service.LekService;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {
	
	@Autowired
	IzvestajService izvestajService;
	
	@Autowired
	PregledService pregledService;
	
	@Autowired
	MedicinskaSestraService medicinskaSestraService;
	
	@Autowired
	ReceptService receptService;
	
	@Autowired
	LekService lekService;
	
	@Autowired
	DijagnozaService dijagnozaService;
	
	@Autowired
	ZdravstveniKartonService zdravstveniKartonService;
	
	@PostMapping(value ="/sacuvaj")
	public ResponseEntity sacuvajIzvestaj(@RequestBody IzvestajDto izvestajDto) {
	   	//System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
	   	try {
	   		System.out.println("PROSAOOO");
	   		//System.out.println(izvestajDto.getPregledId());
	   		Pregled pregled = pregledService.findById(Long.parseLong(izvestajDto.getPregledId()));
	   		//System.out.println(pregled.getId());
	   		MedicinskaSestra medSestra = medicinskaSestraService.findById(5L);
	   		Recept recept = new Recept(false);
	   		for (String idLek : izvestajDto.getLekoviIds()) {
				Lek l = lekService.findById(Long.parseLong(idLek));
				recept.getLekovi().add(l);
			}
	   		recept.setMedicinskaSestra(medSestra);
	   		
	   		Dijagnoza dijagnoza = dijagnozaService.findById(Long.parseLong(izvestajDto.getDijagnozaId()));
	   		
	   		Izvestaj izvestaj = new Izvestaj(izvestajDto.getInformacije(),pregled,recept,dijagnoza);
	   		recept.setIzvestaj(izvestaj);
	   		pregled.setIzvrsen(true);
	   		
	   		pregledService.save(pregled);
	   		receptService.save(recept);
	   		izvestajService.save(izvestaj);
	   		return new ResponseEntity(HttpStatus.OK);
	   	}
	   	catch(Exception ex){
	   		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	   	}
	}
	
}
