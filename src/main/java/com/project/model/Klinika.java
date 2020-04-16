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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="klinike")
public class Klinika {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "klinika_generator")
	@SequenceGenerator(name="klinika_generator", sequenceName = "klinika_seq")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="naziv", unique=false, nullable=false)
	private String naziv;
	
	@Column(name="adresa", unique=false, nullable=false)
	private String adresa;
	
	@Column(name="opis", unique=false, nullable=false)
	private String opis;
	
	@Column(name="ocena", unique=false)
	private Double ocena;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Lekar> lekari;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> sale;

}
