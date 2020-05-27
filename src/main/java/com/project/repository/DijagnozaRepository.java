package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long>{

	boolean existsByNaziv(String naziv);

	Dijagnoza findByNaziv(String imeD);

}
