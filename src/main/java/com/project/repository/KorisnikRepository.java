package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

}
