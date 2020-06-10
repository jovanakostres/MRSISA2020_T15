package com.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.ZahtevZaPregled;

public interface ZahtevZaPregledRepository extends JpaRepository<ZahtevZaPregled, Long>{

	Set<ZahtevZaPregled> findBySala(Object object);

}
