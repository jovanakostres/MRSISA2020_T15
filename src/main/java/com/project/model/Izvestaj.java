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
	private Integer id;
	
	@Column(name="informacije", unique=false, nullable=false)
	private String informacije;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "pregled_id")
	@MapsId
	private Pregled pregled;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "recept_id")
	@MapsId
	private Recept recept;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "izvestaj_id")
	private Set<Dijagnoza> dijagnoza;

	public Izvestaj() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<Dijagnoza> getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Set<Dijagnoza> dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	
	
	
}
