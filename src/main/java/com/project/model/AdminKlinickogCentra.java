package com.project.model;

import java.util.HashSet;
import java.util.Set;

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
@DiscriminatorValue("AKC")
public class AdminKlinickogCentra extends Korisnik{
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "akc_id")
	private Set<Klinika> klinike;
	
	@Column(name = "predefinisan", unique = false, nullable = true)
	private boolean predefinisan;
	
	public AdminKlinickogCentra() {
		
	}
	
	public AdminKlinickogCentra(String email, String lozinka, String ime, String prezime, String adresa, String broj,boolean predefinisan) {
		super(email,lozinka,ime,prezime,adresa,broj);
		this.predefinisan = predefinisan;
	}
	
	public AdminKlinickogCentra(String email, String lozinka, String ime, String prezime, String adresa, String broj,boolean promenaLozinke,boolean predefinisan) {
		super(email,lozinka,ime,prezime,adresa,broj,promenaLozinke);
		this.predefinisan = predefinisan;
	}


	public boolean isPredefinisan() {
		return predefinisan;
	}

	public void setPredefinisan(boolean predefinisan) {
		this.predefinisan = predefinisan;
	}

	public Set<Klinika> getKlinike() {
		return klinike;
	}

	public void setKlinike(Set<Klinika> klinike) {
		this.klinike = klinike;
	}
	
	
	
}
