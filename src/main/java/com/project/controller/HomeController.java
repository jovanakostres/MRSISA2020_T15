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
	
	@GetMapping(value = "/promena_lozinke")
	public String PromenaLozinke() {
		return "promenaLozinke";
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
	
	@GetMapping(value = "akPocetna")
	public String akPocetna() {
		System.out.println("admin klinike");
		return "adm_klinike_html/akPocetna";
	}
	
	@GetMapping(value = "/Izvestaj/{id}")
	public String unosIzvestaja() {
		return "lekar_html/unosIzvestaja";
		
	}

	@GetMapping(value = "/izmena_izvestaja/{id}")
	public String izmenaIzvestaja() {
		return "lekar_html/izmenaIzvestaja";
		
	}

}
