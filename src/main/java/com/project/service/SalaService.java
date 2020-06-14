package com.project.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Sala;
import com.project.repository.LekarRepository;
import com.project.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	LekarService lekarService;

	public List<Sala> findSale(LocalDate datum, LocalTime time) {
		// TODO Auto-generated method stub
		return salaRepository.getSale(datum, time);
	}
	
	public List<Sala> findSaleOp(LocalDate datum, LocalTime time, Long lekarId) {
		// TODO Auto-generated method stub
		List<Sala> sale = new ArrayList<Sala>();
		for(Sala s : salaRepository.getSaleOp(datum, time)) {
			if(s.getKlinika().getId() == lekarService.findById(lekarId).getKlinika().getId()) {
				sale.add(s);
			}
		}
		return salaRepository.getSaleOp(datum, time);
	}

	public Sala findByNaziv(String sala) {
		// TODO Auto-generated method stub
		return salaRepository.findByNaziv(sala);
	}
}
