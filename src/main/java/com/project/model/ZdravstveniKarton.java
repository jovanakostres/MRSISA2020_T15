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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="zdravstveni_kartoni")
public class ZdravstveniKarton {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "karton_generator")
	//@SequenceGenerator(name="karton_generator", sequenceName = "karton_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="visina", unique=false, nullable=true)
	private Integer visina;
	
	@Column(name="tezina", unique=false, nullable=true)
	private Integer tezina;
	
	@Column(name="krvna_grupa", unique=false, nullable=true)
	private String krvnaGrupa;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "pacijent_id")
	@MapsId
	private Pacijent pacijent;
	
	@OneToMany(mappedBy = "zkPacijenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Pregled> pregledi;
	
	public ZdravstveniKarton() {
		
	}

	
	public ZdravstveniKarton(Pacijent pacijent) {
		super();
		this.pacijent = pacijent;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVisina() {
		return visina;
	}

	public void setVisina(Integer visina) {
		this.visina = visina;
	}

	public Integer getTezina() {
		return tezina;
	}

	public void setTezina(Integer tezina) {
		this.tezina = tezina;
	}

	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	
	
	
}
