package com.project.model;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("L")
public class Lekar extends Korisnik{
	@Column(name = "prosecna_ocena", nullable = false)
	private Double prosecnaOcena;
	
	@Column(name="pocetak_rada", unique=false, nullable=false)
	private LocalTime pocetakRada;
	
	@Column(name="kraj_rada", unique=false, nullable=false)
	private LocalTime krajRada;
	
	@OneToMany(mappedBy = "lekar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Klinika klinika;
	
	public Lekar() {
		
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public LocalTime getPocetakRada() {
		return pocetakRada;
	}

	public void setPocetakRada(LocalTime pocetakRada) {
		this.pocetakRada = pocetakRada;
	}

	public LocalTime getKrajRada() {
		return krajRada;
	}

	public void setKrajRada(LocalTime krajRada) {
		this.krajRada = krajRada;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	
}
