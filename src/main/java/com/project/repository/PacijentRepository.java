package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Long>{
	Pacijent findByEmail(String email);
}
