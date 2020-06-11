package com.project.dto;


public class AdminKlinickogCentraDto {
	private Long id;

	private String email;

	private String ime;

	private String prezime;

	private String adresa;

	private String broj;

	public AdminKlinickogCentraDto(Long id, String email, String ime, String prezime, String adresa, String broj) {
		super();
		this.id = id;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
	}
	
	
	
	public AdminKlinickogCentraDto() {
	
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}
	
	
}
