package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Lekar;
import com.project.model.Pregled;

public interface PregledRepository extends JpaRepository<Pregled, Long>{
	
	List<Pregled> findByLekar(Lekar lekar);

	List<Pregled> findByDefinisan(boolean b);
}
