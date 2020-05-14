package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="zahtevi_za_pregled")
public class ZahtevZaPregled {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zahtev_generator")
	@SequenceGenerator(name="zahtev_generator", sequenceName = "zahtev_seq")
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
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

	public ZahtevZaPregled(LocalDate datum, LocalTime vreme, Double trajanjePregleda, Double cena,
			TipPregleda tipPregleda, Sala sala, Lekar lekar, ZdravstveniKarton zkPacijenta) {
		super();
		this.datum = datum;
		this.vreme = vreme;
		this.trajanjePregleda = trajanjePregleda;
		this.cena = cena;
		this.tipPregleda = tipPregleda;
		this.sala = sala;
		this.lekar = lekar;
		this.zkPacijenta = zkPacijenta;
	}
	
	public ZahtevZaPregled() {}

	public ZahtevZaPregled(Pregled p) {
		// TODO Auto-generated constructor stub
		this.datum = p.getDatum();
		this.vreme = p.getVreme();
		this.trajanjePregleda = p.getTrajanjePregleda();
		this.cena = p.getCena();
		this.tipPregleda = p.getTipPregleda();
		this.sala = p.getSala();
		this.lekar = p.getLekar();
		this.zkPacijenta = p.getZkPacijenta();
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

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public ZdravstveniKarton getZkPacijenta() {
		return zkPacijenta;
	}

	public void setZkPacijenta(ZdravstveniKarton zkPacijenta) {
		this.zkPacijenta = zkPacijenta;
	}

	@Override
	public String toString() {
		return "ZahtevZaPregled [id=" + id + ", datum=" + datum + ", vreme=" + vreme + ", trajanjePregleda="
				+ trajanjePregleda + ", cena=" + cena + ", tipPregleda=" + tipPregleda.getIme() + ", sala=" + sala.getNaziv() + ", lekar="
				+ lekar.getIme() + " " + lekar.getPrezime() + ", zkPacijenta=" + zkPacijenta.getPacijent().getLbo() + "]";
	}
	
	
	
	
}
