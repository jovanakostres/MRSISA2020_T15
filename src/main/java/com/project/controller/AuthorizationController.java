package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Korisnik;
import com.project.model.Pacijent;
import com.project.service.KorisnikService;
import com.project.service.PacijentService;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
	
    @Autowired
    PacijentService pacijentService;
    
    @Autowired
    KorisnikService korisnikService;
    
	
    @RequestMapping(value = "/registracija", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Pacijent pacijent) {
    	System.out.println(pacijent);
        try {
            System.out.println("asdasdasdasdsd");
            //pacijent.setConfirmed(false);
            pacijent = pacijentService.save(pacijent);            
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    
	@PostMapping("login")
	public String login(@RequestBody String req){
		String ime = req.split(",")[0].split(":")[1].replace("\"", "");
		String lozinka = req.split(",")[1].split(":")[1].replaceAll("\"|}", "");
		System.out.println("\n\nKorinsik : " + ime + " lozinka: " + lozinka + "\n\n");
		
		Korisnik k = korisnikService.findByEmailAndLozinka(ime, lozinka);
		
		System.out.println(k.getClass());
		
		return "Uspesno logovanje";
	}
	
	
}
