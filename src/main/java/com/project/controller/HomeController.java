package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = "/registracijaPacijenta")
	public String registracijaPacijenta() {
		System.out.println("aaaaa");
		return "registracijaPacijenta";
		
	}
	
	@GetMapping(value = "/login")
	public String login() {
		System.out.println("aaaaa");
		return "login";
		
	}
	
	@GetMapping(value = "/pacijentPocetna")
	public String pacijentPocetna() {
		System.out.println("pacijent");
		return "pacijent_html/pacijentPocetna";
		
	}
	
	
}