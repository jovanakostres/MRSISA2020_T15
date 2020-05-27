package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.DijagnozaDto;
import com.project.model.Dijagnoza;
import com.project.service.DijagnozaService;

@RestController
@RequestMapping("/api/dijagnoza")
public class DijagnozaController {

	@Autowired
	private DijagnozaService dijagnozaService;
	
	@GetMapping(value ="/dijagnoze")
	public List<Dijagnoza> getDijagnoze() {
		return dijagnozaService.findAll();
	}
	
	@PostMapping(value ="/dodaj_dijagnozu")
	public ResponseEntity addDijagnoza(@RequestBody DijagnozaDto d) {
		try{
			if(dijagnozaService.existsByNaziv(d.getNazivDijagnoze()))
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
			dijagnozaService.save(new Dijagnoza(d.getNazivDijagnoze()));
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
