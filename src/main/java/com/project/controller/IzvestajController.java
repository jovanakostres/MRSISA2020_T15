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
import com.project.model.Izvestaj;
import com.project.service.IzvestajService;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {
	
	@Autowired
	IzvestajService izvestajService;
	
	@PostMapping(value ="/sacuvaj")
	public ResponseEntity sacuvajIzvestaj(@RequestBody IzvestajDto izvestajDto) {
	   	//System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
	   	try {
	   		System.out.println("PROSAOOO");
	   		//izvestajService.save(izvestaj);
	   		return new ResponseEntity(HttpStatus.OK);
	   	}
	   	catch(Exception ex){
	   		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	   	}
	}
	
}
