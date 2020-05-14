package com.project.model;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="korisnici")
@Inheritance(strategy=SINGLE_TABLE)
@SequenceGenerator(name="korisnik_generator",initialValue = 8 ,sequenceName = "korisnik_seq")
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public class Korisnik {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_generator")
	@Column(name = "id", updatable = false, nullable = false)
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
	
	public Korisnik() {
		
	}
	
	public Korisnik(Long id, String email, String lozinka, String ime, String prezime, String adresa, String broj) {
		super();
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
	}

	

	public Korisnik(String email, String lozinka, String ime, String prezime, String adresa, String broj) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
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
	
	
}
