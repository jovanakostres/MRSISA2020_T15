package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.LekDto;
import com.project.dto.ZKIzvestajDto;
import com.project.model.Lek;
import com.project.model.ZdravstveniKarton;
import com.project.service.ZdravstveniKartonService;

@RestController
@RequestMapping("/api/zdravstveni_karton")
public class ZdravstveniKartonController {
	
	@Autowired
	ZdravstveniKartonService zdravstveniKartonService;
	
	
	@PostMapping(value ="/sacuvaj")
	public ResponseEntity addLek(@RequestBody ZKIzvestajDto zk) {
		try{	
			ZdravstveniKarton zdrk = zdravstveniKartonService.getOne(zk.getId());
			zdrk.setVisina(zk.getVisina());
			zdrk.setTezina(zk.getTezina());
			zdrk.setKrvnaGrupa(zk.getKrvnaGrupa());
			zdravstveniKartonService.save(zdrk);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
}
