package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Klinika;
import com.project.model.Pacijent;
import com.project.service.KlinikaService;
import com.project.service.PacijentService;

@RestController
@RequestMapping("/api/pacijent")
public class PacijentController {
	
	@Autowired
    PacijentService pacijentService;
	
	@Autowired
    KlinikaService klinikaService;
/**	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProfil() {
    	return "";
    }
    **/
   @GetMapping(value ="/profil")
   public Optional<Pacijent> getProfil() {
	   return pacijentService.findById();
   }
   
   @GetMapping(value ="/klinike")
   public List<Klinika> getKlinike() {
	   Klinika k = new Klinika("klinika1","aaaa","opis1",3.9);
	   List<Klinika> lista = new ArrayList<Klinika>();
	   lista.add(k);
	   lista.add(k);
	   return lista;
	   //return klinikaService.findAll();
   }
   
   
   @RequestMapping(value = "/izmena_profila", method = RequestMethod.POST)
   public ResponseEntity updateProfil(@RequestBody Pacijent pacijent) {
   	System.out.println(pacijent.getIme() + " " + pacijent.getPrezime() );
   	try {
   		System.out.println("PROSAOOO");
   		pacijentService.save(pacijent);
   		return new ResponseEntity(HttpStatus.OK);
   	}
   	catch(Exception ex){
   		return new ResponseEntity(HttpStatus.BAD_REQUEST);
   	}
    
   }
   
}
