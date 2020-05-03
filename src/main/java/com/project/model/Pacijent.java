package com.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("P")
public class Pacijent extends Korisnik{
	
	@Column(name = "lbo", nullable = true)
	private String lbo;
		
	//@OneToOne(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private ZdravstveniKarton zdravstveniKarton;
	
	public Pacijent() {
		
	}
	
	public Pacijent(Long id, String email, String lozinka, String ime, String prezime, String adresa, String broj,String lbo, ZdravstveniKarton zdravstveniKarton) {
		super(id, email, lozinka, ime, prezime, adresa, broj);
		this.lbo = lbo;
	}
	
	public Pacijent(String email, String lozinka, String ime, String prezime, String adresa, String broj,String lbo) {
		super(0L, email, lozinka, ime, prezime, adresa, broj);
		this.lbo = lbo;
	}


	public String getLbo() {
		return lbo;
	}

	public void setLbo(String lbo) {
		this.lbo = lbo;
	}
/**
	public ZdravstveniKarton getZdravstveniKarton() {
		return zdravstveniKarton;
	}

	public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
		this.zdravstveniKarton = zdravstveniKarton;
	}
	**/
	
}
