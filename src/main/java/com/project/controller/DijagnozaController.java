package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Dijagnoza;
import com.project.service.DijagnozaService;

@RestController
@RequestMapping("/api/dijagnoza")
public class DijagnozaController {

	@Autowired
	private DijagnozaService dijagnozaService;
	
	@GetMapping(value ="/dijagnoze")
	public List<Dijagnoza> getDijagnoze() {
		return dijagnozaService.findAll();
	}
}
