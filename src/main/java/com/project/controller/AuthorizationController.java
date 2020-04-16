package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Pacijent;
import com.project.service.PacijentService;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
	
    @Autowired
    PacijentService pacijentService;
	
    @RequestMapping(value = "/registracija", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Pacijent pacijent) {
        try {
            System.out.println("asdasdasdasdsd");
            //pacijent.setConfirmed(false);
            pacijent = pacijentService.save(pacijent);
            
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    
	@PostMapping(value = "/loginKor")
	public String login(@RequestBody String req)
	{
		System.out.println("\n\nKorinsik : " + req + "\n\n");
		return "Uspesno logovanje";
	}
	
}
