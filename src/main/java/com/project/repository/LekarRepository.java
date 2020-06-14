package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.*;

public interface LekarRepository extends JpaRepository<Lekar, Long>{
	
	@Query("select s from Lekar s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3% and s.klinika.id = ?4")
	List<Lekar> filterKorisnikMin(String ime, String prezime, String broj, Long id);
		  //select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena asc
	@Query("select s from Lekar s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3% and s.klinika.id = ?4")
	List<Lekar> filterKorisnikMax(String ime, String prezime, String broj, Long id);
	
	@Query("select s from Lekar s where (s.ime like %?1% or s.prezime like %?1% or s.broj like %?1%) and (s.klinika.id = ?2)")
	List<Lekar> searchKorisnik(String ime, Long id);
	
	@Query("select l from Lekar l")
	List<Lekar> getLekare();
	
	Lekar findByEmail(String email);
	
	@Query("select l from Lekar l where l.ime like ?1 and l.prezime like ?2")
	Lekar findByNameAndSurname(String imeP, String prezimeP);
	
	@Query("select s from Lekar s where s.klinika.id = ?1")
	List<Lekar> getLekariSala(Long id);
	
}

