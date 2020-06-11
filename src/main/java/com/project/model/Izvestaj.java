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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="izvestaji")
public class Izvestaj {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "klinika_generator")
	//@SequenceGenerator(name="klinika_generator", sequenceName = "klinika_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="informacije", unique=false)
	private String informacije;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "pregled_id")
	@MapsId
	private Pregled pregled;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "recept_id")
	private Recept recept;
	
	@OneToOne
    @JoinColumn(name="dijagnoza_id")
	private Dijagnoza dijagnoza;

	public Izvestaj() {
		
	}
	
	public Izvestaj(String informacije, Pregled pregled, Recept recept, Dijagnoza dijagnoza) {
		super();
		this.informacije = informacije;
		this.pregled = pregled;
		this.recept = recept;
		this.dijagnoza = dijagnoza;
	}



	public Izvestaj(String informacije, Pregled pregled) {
		// TODO Auto-generated constructor stub
		super();
		this.informacije = informacije;
		this.pregled = pregled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacije() {
		return informacije;
	}

	public void setInformacije(String informacije) {
		this.informacije = informacije;
	}

	public Pregled getPregled() {
		return pregled;
	}

	public void setPregled(Pregled pregled) {
		this.pregled = pregled;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	
	
	
}
