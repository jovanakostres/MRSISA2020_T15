package com.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("AK")
public class AdminKlinike extends Korisnik{
	
	@ManyToOne
	@JoinColumn(name = "ak_id")
	private Klinika klinika;
	
	public AdminKlinike() {
		
	}

	public AdminKlinike(String email, String lozinka, String ime, String prezime, String adresa, String broj, Klinika klinika) {
		// TODO Auto-generated constructor stub
		super(email,lozinka,ime,prezime,adresa,broj);
		this.klinika = klinika;
	}
	
	public AdminKlinike(String email, String lozinka, String ime, String prezime, String adresa, String broj) {
		// TODO Auto-generated constructor stub
		super(email,lozinka,ime,prezime,adresa,broj);
	}
	
	public AdminKlinike(String email, String lozinka, String ime, String prezime, String adresa, String broj,boolean promenaLozinke, Klinika klinika) {
		// TODO Auto-generated constructor stub
		super(email,lozinka,ime,prezime,adresa,broj,promenaLozinke);
		this.klinika = klinika;
	}
	
	public AdminKlinike(String email, String lozinka, String ime, String prezime, String adresa, String broj,boolean promenaLozinke) {
		// TODO Auto-generated constructor stub
		super(email,lozinka,ime,prezime,adresa,broj,promenaLozinke);
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	
}
