package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Dijagnoza;
import com.project.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;

	public List<Dijagnoza> findAll() {
		// TODO Auto-generated method stub
		return dijagnozaRepository.findAll();
	}
	
}
