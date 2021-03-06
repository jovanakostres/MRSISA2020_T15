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
			//"SELECT t FROM Track t where t.Trackid IN (SELECT pt.Tracks_trackid FROM Playlist_Track pt WHERE pt.Playlist_playlistid = :WhicheverIdWasEntered)"
	
	
	@Query("select s from Klinika s")
	List<Klinika> getKlinike();

}

