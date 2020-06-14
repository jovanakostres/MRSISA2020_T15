package com.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.ZahtevZaOperaciju;

public interface ZahtevZaOperacijuRepository extends JpaRepository<ZahtevZaOperaciju, Long>{

	Set<ZahtevZaOperaciju> findBySala(Object object);

}
