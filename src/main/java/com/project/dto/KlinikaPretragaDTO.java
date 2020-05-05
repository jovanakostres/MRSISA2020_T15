package com.project.dto;

public class KlinikaPretragaDTO {
	private Long id;
	private String naziv;
	private Double ocena;
	private String adresa;
	private Double cena;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public KlinikaPretragaDTO(Long id, String naziv, Double ocena, String adresa, Double cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.ocena = ocena;
		this.adresa = adresa;
		this.cena = cena;
	}
	
	
}
