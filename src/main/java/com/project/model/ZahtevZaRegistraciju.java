package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="zahtevi_za_registraciju")
public class ZahtevZaRegistraciju {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zahtev_generator")
	//@SequenceGenerator(name="zahtev_generator", sequenceName = "zahtev_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "pacijent_id")
	@MapsId
	private Pacijent pacijent;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="datum", unique=false, nullable=false)
	private LocalDate datum;
	
	@Column(name="vreme", unique=false, nullable=false)
	private LocalTime vreme;
	
	public ZahtevZaRegistraciju() {
		
	}

	public ZahtevZaRegistraciju(Pacijent email) {
		super();
		this.pacijent = email;
		this.datum = LocalDate.now();
		this.vreme = LocalTime.now();
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

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}




	
	
}
