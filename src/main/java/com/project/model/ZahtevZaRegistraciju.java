package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="zahtevi_za_registraciju")
public class ZahtevZaRegistraciju {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zahtev_generator")
	@SequenceGenerator(name="zahtev_generator", sequenceName = "zahtev_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="lozinka", unique=false, nullable=false)
	private String lozinka;
	
	@Column(name="ime", unique=false, nullable=false)
	private String ime;
	
	@Column(name="prezime", unique=false, nullable=false)
	private String prezime;
	
	@Column(name="adresa", unique=false, nullable=false)
	private String adresa;
	
	@Column(name="broj", unique=false, nullable=false)
	private String broj;
	
	@Column(name = "lbo", nullable = true)
	private String lbo;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="datum", unique=false, nullable=false)
	private LocalDate datum;
	
	@Column(name="vreme", unique=false, nullable=false)
	private LocalTime vreme;
	
	public ZahtevZaRegistraciju() {
		
	}

	public ZahtevZaRegistraciju(String email, String lozinka, String ime, String prezime, String adresa,
			String broj, String lbo) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
		this.lbo = lbo;
		this.datum = LocalDate.now();
		this.vreme = LocalTime.now();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public String getLbo() {
		return lbo;
	}

	public void setLbo(String lbo) {
		this.lbo = lbo;
	}

	




	
	
}
