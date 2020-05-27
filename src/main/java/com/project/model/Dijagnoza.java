package com.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="dijagnoze")
public class Dijagnoza {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dijagnoza_generator")
	@SequenceGenerator(name="dijagnoza_generator",initialValue = 3 ,sequenceName = "dijagnoza_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="naziv", unique=true, nullable=false)
	private String naziv;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sifarnik", joinColumns = @JoinColumn(name = "dijagnoza_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"))
	private Set<Lek> lekovi;

	public Dijagnoza() {
		
	}
	
	
	public Dijagnoza(String naziv) {
		super();
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	
	
	
}
