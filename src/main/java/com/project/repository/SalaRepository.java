package com.project.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{

	@Query("select s from Sala s left join Pregled p on s.id = p.sala.id where (p.datum != ?1 or p.datum is null) and (p.vremeOd!= ?2 or p.vremeOd is null)")
	List<Sala> getSale(LocalDate datum, LocalTime vreme);

	Sala findByNaziv(String sala);
	
}
