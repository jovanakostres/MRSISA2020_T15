package com.project.controller;

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

import com.project.model.Pacijent;
import com.project.service.PacijentService;

@RestController
@RequestMapping("/api/pacijent")
public class PacijentController {
	
	@Autowired
    PacijentService pacijentService;
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProfil() {
    	return "";
    }
    
   @GetMapping(value ="/klinike")
   public Optional<Pacijent> getKlinike() {
	   return pacijentService.findById();
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
