package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.TipPregleda;
import com.project.repository.PacijentRepository;
import com.project.repository.TipPregledaRepository;

@Service
public class TIpPregledaService {

	@Autowired
	private TipPregledaRepository tipPregledaRepository;
	
	public List<TipPregleda> findAll()
	{
		return tipPregledaRepository.findAll();
	}
}
