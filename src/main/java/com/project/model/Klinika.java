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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="klinike")
public class Klinika {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "klinika_generator")
	@SequenceGenerator(name="klinika_generator", sequenceName = "klinika_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="naziv", unique=false, nullable=false)
	private String naziv;
	
	@Column(name="adresa", unique=false, nullable=false)
	private String adresa;
	
	@Column(name="opis", unique=false, nullable=false)
	private String opis;
	
	@Column(name="ocena", unique=false)
	private Double ocena;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Lekar> lekari;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<MedicinskaSestra> medicinskeSestre;
	
	@OneToMany(mappedBy = "klinika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Set<Sala> sale;
	
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="klinika_pacijenti", joinColumns=@JoinColumn(name="klinika_id", referencedColumnName = "id"), inverseJoinColumns=@JoinColumn(name="pacijent_id", referencedColumnName = "id")) 
	private Set<Pacijent> pacijenti;

	public Klinika(String naziv, String adresa, String opis, Double ocena) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.ocena = ocena;
	}
	
	public Klinika() {
		
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<MedicinskaSestra> getMedicinskeSestre() {
		return medicinskeSestre;
	}

	public void setMedicinskeSestre(Set<MedicinskaSestra> medicinskeSestre) {
		this.medicinskeSestre = medicinskeSestre;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Set<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(Set<Lekar> lekari) {
		this.lekari = lekari;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(Set<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}
	
	

}
