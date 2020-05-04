package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="pregledi")
public class Pregled {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pregled_generator")
	@SequenceGenerator(name="pregled_generator", sequenceName = "pregled_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="datum", unique=false, nullable=false)
	private LocalDate datum;
	
	@Column(name="vreme", unique=false, nullable=false)
	private LocalTime vreme;
	
	@Column(name="trajanje_pregleda", unique=false, nullable=false)
	private Double trajanjePregleda;
	
	@Column(name="cena", unique=false, nullable=false)
	private Double cena;
	
	@Column(name="izvrsen", unique=false, nullable=false)
	private Boolean izvrsen;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tip_pregleda", unique=false, nullable=false)
	private TipPregleda tipPregleda;
	
	//@OneToOne(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Izvestaj izvestaj;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Sala sala;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Lekar lekar;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private ZdravstveniKarton zkPacijenta;
	
	public Pregled() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}

	public Double getTrajanjePregleda() {
		return trajanjePregleda;
	}

	public void setTrajanjePregleda(Double trajanjePregleda) {
		this.trajanjePregleda = trajanjePregleda;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}


	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public void setZkPacijenta(ZdravstveniKarton zkPacijenta) {
		this.zkPacijenta = zkPacijenta;
	}

	public Boolean getIzvrsen() {
		return izvrsen;
	}

	public void setIzvrsen(Boolean izvrsen) {
		this.izvrsen = izvrsen;
	}

	public ZdravstveniKarton getZkPacijenta() {
		return zkPacijenta;
	}

	
}
