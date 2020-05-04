package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PregledDto {

	private Long id;
	
	private String imeP;
	
	private String prezimeP;
	
	private String sala;
	
	private LocalDate datum;
	
	private LocalTime vreme;
	
	private Double trajanje;
	
	private Double cena;

	
	public PregledDto(Long id, String imeP, String prezimeP, String sala, LocalDate datum, LocalTime vreme,
			Double trajanje, Double cena) {
		super();
		this.id = id;
		this.imeP = imeP;
		this.prezimeP = prezimeP;
		this.sala = sala;
		this.datum = datum;
		this.vreme = vreme;
		this.trajanje = trajanje;
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImeP() {
		return imeP;
	}

	public void setImeP(String imeP) {
		this.imeP = imeP;
	}

	public String getPrezimeP() {
		return prezimeP;
	}

	public void setPrezimeP(String prezimeP) {
		this.prezimeP = prezimeP;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}

	public Double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Double trajanje) {
		this.trajanje = trajanje;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
	
}
