package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.AdminKlinickogCentra;

public interface AdminKlinickogCentraRepository extends JpaRepository<AdminKlinickogCentra, Long>{

	AdminKlinickogCentra findByEmail(String username);
	
}
