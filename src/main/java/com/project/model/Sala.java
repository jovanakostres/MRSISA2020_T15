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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="sale")
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_generator")
	@SequenceGenerator(name="sala_generator", sequenceName = "sala_seq")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="naziv", unique=true, nullable=false)
	private String naziv;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Klinika klinika;
	
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi;

	public Sala() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	
	
}
