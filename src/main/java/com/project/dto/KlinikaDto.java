package com.project.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class KlinikaDto {
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Long> admini;
	
	public KlinikaDto(String naziv, String adresa, String opis, List<Long> admini) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.admini = admini;
	}
	
	public KlinikaDto(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	

	public KlinikaDto() {
		super();
	}

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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public List<Long> getAdmini() {
		return admini;
	}
	public void setAdmini(List<Long> admini) {
		this.admini = admini;
	}
	
	
}
