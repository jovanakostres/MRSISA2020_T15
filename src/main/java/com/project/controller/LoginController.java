package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping(value = "/loginKor")
	public String login(@RequestBody String req)
	{
		System.out.println("\n\nKorinsik : " + req + "\n\n");
		return "Uspesno logovanje";
	}
	
}
