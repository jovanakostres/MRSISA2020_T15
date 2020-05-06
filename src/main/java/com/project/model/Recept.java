package com.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="recepti")
public class Recept {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recept_generator")
	@SequenceGenerator(name="recept_generator", sequenceName = "recept_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="overen", unique=false, nullable=true)
	private boolean overen;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recept_lekovi", joinColumns = @JoinColumn(name = "recept_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"))
	private Set<Lek> lekovi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private MedicinskaSestra medicinskaSestra;
	
	@OneToOne(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Izvestaj izvestaj;

	public Recept() {
		
	}
	
	public Recept(boolean overen) {
		super();
		this.overen = overen;
		this.lekovi = new HashSet<Lek>();
	}

	public Recept(boolean overen, Set<Lek> lekovi, MedicinskaSestra medicinskaSestra) {
		super();
		this.overen = overen;
		this.lekovi = lekovi;
		this.medicinskaSestra = medicinskaSestra;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOveren() {
		return overen;
	}

	public void setOveren(boolean overen) {
		this.overen = overen;
	}

	public MedicinskaSestra getMedicinskaSestra() {
		return medicinskaSestra;
	}



	public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
		this.medicinskaSestra = medicinskaSestra;
	}



	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public Izvestaj getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(Izvestaj izvestaj) {
		this.izvestaj = izvestaj;
	}
	
	

}
