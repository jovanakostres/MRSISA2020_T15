package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.AdminKlinickogCentra;
import com.project.repository.AdminKlinickogCentraRepository;

@Service
public class AdminKlinickogCentraService {
	
	@Autowired
	private AdminKlinickogCentraRepository akcRepository;
	
	public AdminKlinickogCentra findById(Long idK) {
		// TODO Auto-generated method stub
		return akcRepository.findById(idK).orElseGet(null);
	}

	public AdminKlinickogCentra findByEmail(String username) {
		// TODO Auto-generated method stub
		return akcRepository.findByEmail(username);
	}

	public void save(AdminKlinickogCentra adminKlinickogCentra) {
		// TODO Auto-generated method stub
		akcRepository.save(adminKlinickogCentra);
	}

}
