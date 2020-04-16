package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Long>{

}
