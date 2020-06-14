package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="pregledi")
public class Pregled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pregled_generator")
	@SequenceGenerator(name="pregled_generator",initialValue = 2, sequenceName = "pregled_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Version
    private Long version;
	
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
	
	@Column(name = "operacija", unique = false, nullable = false)
	private Boolean operacija;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private TipPregleda tipPregleda;
	
	//@OneToOne(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Izvestaj izvestaj;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	//@JoinColumn(name="sala_id")
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

	public Boolean getDefinisan() {
		return definisan;
	}

	public void setDefinisan(Boolean definisan) {
		this.definisan = definisan;
	}
	
	public Boolean getOperacija() {
		return operacija;
	}

	public void setOperacija(Boolean operacija) {
		this.operacija = operacija;
	}

	@Override
	public String toString() {
		return "Pregled [datum=" + datum + ", vreme pocetka=" + vremeOd + ", vreme zavrsetka=" + vremeDo + ", tipPregleda=" + tipPregleda.getIme() + ", lekar=" + lekar.getIme() 
		+ " " + lekar.getPrezime() + ", zkPacijenta=" + zkPacijenta.getPacijent().getLbo() + "]";
	}
	
}
