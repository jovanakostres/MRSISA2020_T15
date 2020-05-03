package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.*;

public interface LekarRepository extends JpaRepository<Lekar, Long>{
	
	@Query("select s from Korisnik s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3%")
	List<Lekar> filterKorisnikMin(String ime, String prezime, String broj);
		  //select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena asc
	@Query("select s from Korisnik s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3%")
	List<Lekar> filterKorisnikMax(String ime, String prezime, String broj);
	
	@Query("select s from Korisnik s where s.ime like %?1% or s.prezime like %?1% or s.broj like %?1%")
	List<Lekar> searchKorisnik(String ime);
	
}
