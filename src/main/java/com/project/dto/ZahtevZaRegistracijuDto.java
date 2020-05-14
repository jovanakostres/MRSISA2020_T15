package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ZahtevZaRegistracijuDto {
	private Long id;
	
	private String emailP;
	
	private String imeP;
	
	private String prezimeP;
	
	private LocalDate datum;
	
	private LocalTime vreme;

	public ZahtevZaRegistracijuDto(Long id, String emailP, String imeP, String prezimeP, LocalDate datum,
			LocalTime vreme) {
		super();
		this.id = id;
		this.emailP = emailP;
		this.imeP = imeP;
		this.prezimeP = prezimeP;
		this.datum = datum;
		this.vreme = vreme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailP() {
		return emailP;
	}

	public void setEmailP(String emailP) {
		this.emailP = emailP;
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
	
	
}
