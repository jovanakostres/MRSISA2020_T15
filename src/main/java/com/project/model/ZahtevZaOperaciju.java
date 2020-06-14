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
@Table(name="zahtevi_za_operacije")
public class ZahtevZaOperaciju {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zahtevi_operacija_generator")
	@SequenceGenerator(name="zahtevi_operacija_generator",initialValue = 2, sequenceName = "zahtevi_operacija_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="lekar_zakazao_id", unique=true, nullable=false)
	private Long lekarId;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="datum", unique=false, nullable=false)
	private LocalDate datum;
	
	@Column(name="vreme_od", unique=false, nullable=false)
	private LocalTime vremeOd;
	
	@Column(name="vreme_do", unique=false, nullable=false)
	private LocalTime vremeDo;
	
	@Column(name="cena", unique=false, nullable=false)
	private Double cena;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private TipPregleda tipPregleda;
	
	//@OneToOne(mappedBy = "pregled", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Izvestaj izvestaj;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	//@JoinColumn(name="sala_id")
	private Sala sala;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "zahtevi_operacije_lekari", joinColumns = @JoinColumn(name = "zahtev_operacija_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "lekar_id", referencedColumnName = "id"))
	private Set<Lekar> lekari;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private ZdravstveniKarton zkPacijenta;
	
	@Column(name="datumprijave", unique=false, nullable=false)
	private LocalDate datumPrijave;
	
	@Column(name="vremeprijave", unique=false, nullable=false)
	private LocalTime vremePrijave;
	
	public ZahtevZaOperaciju() {}
	
	public ZahtevZaOperaciju(LocalDate datum, LocalTime vremeOd, LocalTime vremeDo, Double cena,
			TipPregleda tipPregleda, Sala sala, Set<Lekar> lekari, ZdravstveniKarton zkPacijenta,
			LocalDate datumPrijave, LocalTime vremePrijave, Long lekarId) {
		super();
		this.datum = datum;
		this.vremeOd = vremeOd;
		this.vremeDo = vremeDo;
		this.cena = cena;
		this.tipPregleda = tipPregleda;
		this.sala = sala;
		this.lekari = lekari;
		this.zkPacijenta = zkPacijenta;
		this.datumPrijave = datumPrijave;
		this.vremePrijave = vremePrijave;
		this.lekarId = lekarId;
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

	public void setLekari(Set<Lekar> lekari) {
		this.lekari = lekari;
	}

	public ZdravstveniKarton getZkPacijenta() {
		return zkPacijenta;
	}

	public void setZkPacijenta(ZdravstveniKarton zkPacijenta) {
		this.zkPacijenta = zkPacijenta;
	}

	public LocalDate getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(LocalDate datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public LocalTime getVremePrijave() {
		return vremePrijave;
	}

	public void setVremePrijave(LocalTime vremePrijave) {
		this.vremePrijave = vremePrijave;
	}

	public Long getLekarId() {
		return lekarId;
	}

	public void setLekarId(Long lekarId) {
		this.lekarId = lekarId;
	}
	
	
}
