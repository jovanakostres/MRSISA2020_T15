package com.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Klinika;
import com.project.model.Pacijent;
import com.project.service.LekarService;

@RestController
@RequestMapping("/api/lekar")
public class LekarController {

	@Autowired
    LekarService lekarService;
	
	   @GetMapping(value ="/pacijentiklinike")
	   public Set<Pacijent> getPacijenti() {
		   /*
		   Pacijent k = new Pacijent("aaa@email.com", "aaa", "aaa", "aaa", "aaa", "aaa","aaa");
		   Set<Pacijent> lista = new HashSet<Pacijent>();
		   lista.add(k);
		   lista.add(k);
		   return lista;
		   */
		   return lekarService.getPacijentiKlinike(3L);
	   }
	
}
