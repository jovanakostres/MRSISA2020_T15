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
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_generator")
	@SequenceGenerator(name="korisnik_generator", sequenceName = "korisnik_seq")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
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
	
	public Korisnik(Integer id, String email, String lozinka, String ime, String prezime, String adresa, String broj) {
		super();
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.broj = broj;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
