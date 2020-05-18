package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.ZauzetoVreme;
import com.project.repository.ZauzetoVremeRepository;

@Service
public class ZauzetoVremeService {
	
	@Autowired
	private ZauzetoVremeRepository zauzetoVremeRepository;

	public List<ZauzetoVreme> findAll() {
		// TODO Auto-generated method stub
		return zauzetoVremeRepository.findAll();
	}
	
	
}
