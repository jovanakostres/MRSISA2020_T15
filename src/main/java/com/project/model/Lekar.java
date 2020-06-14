package com.project.model;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@DiscriminatorValue("L")
public class Lekar extends Korisnik{
	@Column(name = "prosecna_ocena", nullable = true)
	private Double prosecnaOcena;
	
	@Column(name="pocetak_rada", unique=false, nullable=true)
	private LocalTime pocetakRada;
	
	@Column(name="kraj_rada", unique=false, nullable=true)
	private LocalTime krajRada;
	
	@OneToMany(mappedBy = "lekar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Pregled> pregledi;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "lekari_operacije", joinColumns = @JoinColumn(name = "lekar_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "operacija_id", referencedColumnName = "id"))
	private Set<Operacija> operacije;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Klinika klinika;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private TipPregleda tipPregleda;
	
	@OneToMany(mappedBy = "lekar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ZauzetoVreme> zauzetoVreme;
	
	public Lekar() {
		
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public LocalTime getPocetakRada() {
		return pocetakRada;
	}

	public void setPocetakRada(LocalTime pocetakRada) {
		this.pocetakRada = pocetakRada;
	}

	public LocalTime getKrajRada() {
		return krajRada;
	}

	public void setKrajRada(LocalTime krajRada) {
		this.krajRada = krajRada;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public TipPregleda getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(TipPregleda tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Set<ZauzetoVreme> getZauzetoVreme() {
		return zauzetoVreme;
	}

	public void setZauzetoVreme(Set<ZauzetoVreme> zauzetoVreme) {
		this.zauzetoVreme = zauzetoVreme;
	}

	
}
