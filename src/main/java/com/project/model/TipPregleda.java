package com.project.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="tip_pregleda")
public class TipPregleda {
	@Column(name = "ime", nullable = true)
	private String ime;
	
	@Column(name="cena", unique=false, nullable=true)
	private Double cena;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tip_pregleda_generator")
	@SequenceGenerator(name="tip_pregleda_generator",initialValue = 3, sequenceName = "tip_pregleda_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	public TipPregleda()
	{
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}