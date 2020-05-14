package com.project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = "/registracijaPacijenta")
	public String registracijaPacijenta() {
		System.out.println("aaaaa");
		return "registracijaPacijenta";
		
	}
	
	
	@GetMapping(value = "/pacijentPocetna")
	public String pacijentPocetna() {
		System.out.println("pacijent");
		return "pacijent_html/pacijentPocetna";
		
	}
	
	@GetMapping(value = "/lekarPocetna")
	public String lekarPocetna() {
		System.out.println("lekar");
		return "lekar_html/lekarPocetna";
		
	}
	
	@GetMapping(value = "/akcPocetna")
	public String akcPocetna() {
		System.out.println("lekar");
		return "akc_html/akcPocetna";
		
	}
	
	@GetMapping(value = "/Izvestaj/{id}")
	public String unosIzvestaja() {
		return "lekar_html/unosIzvestaja";
		
	}
	
	
}
