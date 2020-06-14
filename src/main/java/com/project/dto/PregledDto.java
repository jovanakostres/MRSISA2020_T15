package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PregledDto {

	private Long id;
	
	private String imeP;
	
	private String prezimeP;
	
	private String sala;
	
	private LocalDate datum;
	
	private LocalTime vremeOd;
	
	private LocalTime vremeDo;
	
	private Double cena;

	private boolean izvrsen;
	
	private String tipOp;
	
	public PregledDto() {}
	
	public PregledDto(Long id, String imeP, String prezimeP, String sala, LocalDate datum, LocalTime vremeOd,
			LocalTime vremeDo, Double cena, boolean izvrsen, String tipOp) {
		super();
		this.id = id;
		this.imeP = imeP;
		this.prezimeP = prezimeP;
		this.sala = sala;
		this.datum = datum;
		this.vremeOd = vremeOd;
		this.vremeDo = vremeDo;
		this.cena = cena;
		this.izvrsen = izvrsen;
		this.tipOp = tipOp;
	}
	
	public PregledDto(String id, String imeP, String prezimeP, String sala, String datum, String vremeOd,
			String vremeDo, String cena, boolean izvrsen, String tipOp) {
		super();
		this.id = Long.parseLong(id);
		this.imeP = imeP;
		this.prezimeP = prezimeP;
		this.sala = sala;
		this.datum = LocalDate.parse(datum);
		this.vremeOd = LocalTime.parse(vremeOd);
		this.vremeDo = LocalTime.parse(vremeDo);
		this.cena = Double.parseDouble(cena);
		this.izvrsen = izvrsen;
		this.tipOp = tipOp;
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

	public LocalTime getVremeOd() {
		return vremeOd;
	}

	public void setVremeOd(LocalTime vremeOd) {
		this.vremeOd = vremeOd;
	}

	public LocalTime getVremeDo() {
		return vremeDo;
	}

	public void setVremeDo(LocalTime vremeDo) {
		this.vremeDo = vremeDo;
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

	public boolean isIzvrsen() {
		return izvrsen;
	}

	public void setIzvrsen(boolean izvrsen) {
		this.izvrsen = izvrsen;
	}

	public String getTipOp() {
		return tipOp;
	}

	public void setTipOp(String tipOp) {
		this.tipOp = tipOp;
	}

	@Override
	public String toString() {
		return "PregledDto [id=" + id + ", imeP=" + imeP + ", prezimeP=" + prezimeP + ", sala=" + sala + ", datum="
				+ datum + ", vremeOd=" + vremeOd + ", vremeDo=" + vremeDo + ", cena=" + cena + ", izvrsen=" + izvrsen
				+ ", tipOp=" + tipOp + "]";
	}
		
	
}
