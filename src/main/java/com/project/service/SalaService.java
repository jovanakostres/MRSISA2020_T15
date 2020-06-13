package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Sala;
import com.project.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;

	public List<Sala> findAll(LocalDate datum, LocalTime time) {
		// TODO Auto-generated method stub
		return salaRepository.getSale(datum, time);
	}
}
