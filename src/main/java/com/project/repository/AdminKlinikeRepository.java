package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.AdminKlinike;

public interface AdminKlinikeRepository extends JpaRepository<AdminKlinike, Long>{
	
	@Query("select s from AdminKlinike s where s.klinika is null ")
	List<AdminKlinike> getAdminiBezKlinike();

}
