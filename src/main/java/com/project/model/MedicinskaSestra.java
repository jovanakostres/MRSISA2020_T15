package com.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("MS")
public class MedicinskaSestra extends Korisnik{
	
	@OneToMany(mappedBy = "medicinskaSestra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recept> recepti;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Klinika klinika;

	public MedicinskaSestra() {

	}

	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
	
}
