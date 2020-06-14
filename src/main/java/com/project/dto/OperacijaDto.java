package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.Lekar;

public class OperacijaDto {

	private Long id;
	
	private Long lekarZakazaoId;
	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Long> lekari_ids;
	
	private String sala;
	
	private LocalDate datum;
	
	private LocalTime vremeOd;
	
	private LocalTime vremeDo;
	
	private Double cena;

	private boolean izvrsen;
	
	private String tipOp;
	
	public OperacijaDto() {}

	public OperacijaDto(Long id, List<Long> lekari_ids, String sala, LocalDate datum, LocalTime vremeOd,
			LocalTime vremeDo, Double cena, boolean izvrsen, String tipOp) {
		super();
		this.id = id;
		this.lekari_ids = lekari_ids;
		this.sala = sala;
		this.datum = datum;
		this.vremeOd = vremeOd;
		this.vremeDo = vremeDo;
		this.cena = cena;
		this.izvrsen = izvrsen;
		this.tipOp = tipOp;
	}
	
	public OperacijaDto(Long id, Set<Lekar> lekari, String sala, LocalDate datum, LocalTime vremeOd,
			LocalTime vremeDo, Double cena, boolean izvrsen, String tipOp, Long lekarZakazaoId) {
		super();
		this.id = id;
		this.lekari_ids = new ArrayList<Long>();
		for(Lekar l : lekari) {
			this.lekari_ids.add(l.getId());
		}
		this.sala = sala;
		this.datum = datum;
		this.vremeOd = vremeOd;
		this.vremeDo = vremeDo;
		this.cena = cena;
		this.izvrsen = izvrsen;
		this.tipOp = tipOp;
		this.lekarZakazaoId = lekarZakazaoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getLekari_ids() {
		return lekari_ids;
	}

	public void setLekari_ids(List<Long> lekari_ids) {
		this.lekari_ids = lekari_ids;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
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

	public Long getLekarZakazaoId() {
		return lekarZakazaoId;
	}

	public void setLekarZakazaoId(Long lekarZakazaoId) {
		this.lekarZakazaoId = lekarZakazaoId;
	}
	
	
}
