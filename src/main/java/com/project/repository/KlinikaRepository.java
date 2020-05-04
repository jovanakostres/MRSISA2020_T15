<<<<<<< HEAD
package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Klinika;

public interface KlinikaRepository extends JpaRepository<Klinika, Long>{
	
	@Query("select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena asc")
	List<Klinika> filterKlinikeMin(String naziv, String adresa, String opis);
	
	@Query("select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena desc")
	List<Klinika> filterKlinikeMax(String naziv, String adresa, String opis);
	
	@Query("select s from Klinika s where s.naziv like %?1% or s.adresa like %?1% or s.opis like %?1%")
	List<Klinika> searchKlinike(String naziv);

}
=======
package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Klinika;

public interface KlinikaRepository extends JpaRepository<Klinika, Long>{
	
	@Query("select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena asc")
	List<Klinika> filterKlinikeMin(String naziv, String adresa, String opis);
	
	@Query("select s from Klinika s where s.naziv like %?1% and s.adresa like %?2% and s.opis like %?3% order by s.ocena desc")
	List<Klinika> filterKlinikeMax(String naziv, String adresa, String opis);
	
	@Query("select s from Klinika s where s.naziv like %?1% or s.adresa like %?1% or s.opis like %?1%")
	List<Klinika> searchKlinike(String naziv);

}
>>>>>>> 1494f90d8123a17211e26cc90e9906eb700f3301
