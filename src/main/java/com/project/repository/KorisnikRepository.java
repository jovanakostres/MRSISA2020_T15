package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{	
	      
	@Query("select s from Korisnik s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3%")
	List<Korisnik> filterKorisnikMin(String ime, String prezime, String broj);
		  //select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena asc
	@Query("select s from Korisnik s where s.ime like %?1% and s.prezime like %?2% and s.broj like %?3%")
	List<Korisnik> filterKorisnikMax(String ime, String prezime, String broj);
		//select s from Klinika s where s.naziv like %?1% or s.adresa like %?1% or s.opis like %?1%
	@Query("select s from Korisnik s where s.ime like %?1% or s.prezime like %?1% or s.broj like %?1%")
	List<Korisnik> searchKorisnik(String ime);
	
	Korisnik findByEmail(String email);
	Korisnik findByEmailAndLozinka(String email, String lozinka);
}

