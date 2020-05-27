package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Lek;

public interface LekRepository extends JpaRepository<Lek, Long>{

	boolean existsByNaziv(String nazivLeka);

	Lek findByNaziv(String imeL);

}
