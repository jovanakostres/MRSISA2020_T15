package com.project.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class LekarDTO {
	public Long id ;
	public String ime;
	public String prezime;
	public Double ocena;
	public String naziv;
	public List<LocalTime> slobodniTermini;
	public LekarDTO(Long id, String ime, String prezime, Double ocena, String naziv) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.ocena = ocena;
		this.naziv = naziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<LocalTime> getSlobodniTermini() {
		return slobodniTermini;
	}
	public void setSlobodniTermini(List<LocalTime> slobodniTermini) {
		this.slobodniTermini = slobodniTermini;
	}
		
}
