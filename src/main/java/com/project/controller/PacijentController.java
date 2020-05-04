package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.*;
import com.project.service.*;

@RestController
@RequestMapping("/api/pacijent")
public class PacijentController {
	
	@Autowired
    PacijentService pacijentService;
	
	@Autowired
    KlinikaService klinikaService;
	
	@Autowired
	LekarService lekarService;
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
	   return klinikaService.findAll();
   }
   
   @PostMapping(value ="/lekari")
   public Set<Lekar> getLekari(@RequestBody String index) {
	   String ind = index.split(":")[1].replace("\"","").replace("}", "");
	   System.out.println("KLINIKA " + ind);
	   return klinikaService.getLekari(Long.parseLong(ind));
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
   
   @RequestMapping(value = "/filter_klinika", method = RequestMethod.POST)
   public List<Klinika> filter_klinika(@RequestBody String pacijent) {
	   String[] params = pacijent.split(",");
	   String naziv, adresa, opis, minmax;
	   naziv = params[0].split(":")[1].replace("\"", "");
	   adresa = params[1].split(":")[1].replace("\"", "");
	   opis = params[2].split(":")[1].replace("\"", "");
	   minmax = params[3].split(":")[1].replace("\"","").replace("}", "");
	   System.out.println("Naziv:  " + naziv + "  Adresa: " + adresa +"  Opis : " + opis);
	   return klinikaService.filter(naziv, adresa, opis, minmax);	   
   }   
   
   //pretraga_klinika
   @RequestMapping(value = "/pretraga_klinika", method = RequestMethod.POST)
   public List<Klinika> pretraga_klinika(@RequestBody String pacijent) {
	   String param = pacijent.split(":")[1].replace("\"", "").replace("}", "");
	   System.out.println("PRETRAGA PARAM : " + param);
	   return klinikaService.search(param);
   }   
   
   
   @RequestMapping(value = "/filter_lekar", method = RequestMethod.POST)
   public List<Lekar> filter_lekar(@RequestBody String pacijent) {
	   String[] params = pacijent.split(",");
	   String ime, prez, broj, minmax;
	   ime = params[0].split(":")[1].replace("\"", "");
	   prez = params[1].split(":")[1].replace("\"", "");
	   broj = params[2].split(":")[1].replace("\"", "");
	   minmax = params[3].split(":")[1].replace("\"","").replace("}", "");
	   return lekarService.filter(ime, prez, broj, minmax);	   
   }   
   
   //pretraga_klinika
   @RequestMapping(value = "/pretraga_lekar", method = RequestMethod.POST)
   public List<Lekar> pretraga_lekar(@RequestBody String pacijent) {
	   String param = pacijent.split(":")[1].replace("\"", "").replace("}", "");
	   System.out.println("PRETRAGA PARAM : " + param);
	   return lekarService.search(param);
   }   
   
}
