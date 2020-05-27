package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.LekDto;
import com.project.model.Lek;
import com.project.service.LekService;

@RestController
@RequestMapping("/api/lek")
public class LekController {
	
	@Autowired
	private LekService lekService;
	
	@PostMapping(value ="/dodaj_lek")
	public ResponseEntity addLek(@RequestBody LekDto l) {
		try{
			if(lekService.existsByNaziv(l.getNazivLeka()))
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
			lekService.save(new Lek(l.getNazivLeka()));
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		//return dijagnozaService.findAll();
	}
}
