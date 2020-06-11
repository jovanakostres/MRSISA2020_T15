package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.AdminKlinike;
import com.project.repository.AdminKlinikeRepository;

@Service
public class AdminKlinikeService {

	@Autowired
	private AdminKlinikeRepository akRepository;

	public AdminKlinike findById(Long idK) {
		// TODO Auto-generated method stub
		return akRepository.findById(idK).orElseGet(null);
	}

	public List<AdminKlinike> getAdminiBezKlinike() {
		// TODO Auto-generated method stub
		return akRepository.getAdminiBezKlinike();
	}

	public void save(AdminKlinike adminKlinike) {
		// TODO Auto-generated method stub
		akRepository.save(adminKlinike);
	}
	
	
}
