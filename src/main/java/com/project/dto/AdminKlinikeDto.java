package com.project.dto;

public class AdminKlinikeDto {
	private Long id;
	private String email;
	private String ime;
	private String prezime;
	private String adresa;
	private String broj;
	private Long idK;
	
	public AdminKlinikeDto(Long id, String ime, String prezime) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		
	}
	

	public AdminKlinikeDto(String email, String ime, String prezime, String adresa, String broj, Long idK) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
		this.idK = idK;
	}
	
	
	
	public AdminKlinikeDto() {
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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

	public Long getIdK() {
		return idK;
	}

	public void setIdK(Long idK) {
		this.idK = idK;
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
	
	
}
