package com.project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Pacijent;
import com.project.model.ZahtevZaPregled;

public interface ZahtevZaPregledRepository extends JpaRepository<ZahtevZaPregled, Long>{

	Set<ZahtevZaPregled> findBySala(Object object);

	@Query("select z from ZahtevZaPregled z where z.pacijent.id = ?1")
	List<ZahtevZaPregled> findByPacijent(Long p);

}
