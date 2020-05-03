package com.project.model;

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
	private Integer id;
	
	@Column(name="opis", unique=false, nullable=true)
	private String opis;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "recept_id")
	private Set<Lek> lekovi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private MedicinskaSestra medicinskaSestra;

	public Recept() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	
	

}
