package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="operacije")
public class Operacija {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operacija_generator")
	@SequenceGenerator(name="operacija_generator",initialValue = 2, sequenceName = "operacija_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="datum", unique=false, nullable=false)
	private LocalDate datum;
	
	@Column(name="vreme_od", unique=false, nullable=false)
	private LocalTime vremeOd;
	
	@Column(name="vreme_do", unique=false, nullable=false)
	private LocalTime vremeDo;
	
	@Column(name="cena", unique=false, nullable=false)
	private Double cena;	
	
	@Column(name="izvrsen", unique=false, nullable=false)
	private Boolean izvrsen;
	
	@Column(name = "definisan", unique = false, nullable = false)
	private Boolean definisan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private TipPregleda tipPregleda;
	
	//@OneToOne(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Izvestaj izvestaj;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	//@JoinColumn(name="sala_id")
	private Sala sala;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "lekari_operacije", joinColumns = @JoinColumn(name = "operacija_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "lekar_id", referencedColumnName = "id"))
	private Set<Lekar> lekari;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private ZdravstveniKarton zkPacijenta;
	
	public Operacija() {}

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

	public LocalTime getVremeOd() {
		return vremeOd;
	}

	public void setVremeOd(LocalTime vremeOd) {
		this.vremeOd = vremeOd;
	}

	public LocalTime getVremeDo() {
		return vremeDo;
	}

	public void setVremeDo(LocalTime vremeDo) {
		this.vremeDo = vremeDo;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Boolean getIzvrsen() {
		return izvrsen;
	}

	public void setIzvrsen(Boolean izvrsen) {
		this.izvrsen = izvrsen;
	}

	public Boolean getDefinisan() {
		return definisan;
	}

	public void setDefinisan(Boolean definisan) {
		this.definisan = definisan;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(Set<Lekar> lekar) {
		this.lekari = lekar;
	}

	public ZdravstveniKarton getZkPacijenta() {
		return zkPacijenta;
	}

	public void setZkPacijenta(ZdravstveniKarton zkPacijenta) {
		this.zkPacijenta = zkPacijenta;
	}
	
	
}
