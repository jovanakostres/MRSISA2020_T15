package com.project.dto;

public class LekarDTO {
	public String ime;
	public String prezime;
	public Double ocena;
	public String naziv;
	public LekarDTO(String ime, String prezime, Double ocena, String naziv) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.ocena = ocena;
		this.naziv = naziv;
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
	
	
}
