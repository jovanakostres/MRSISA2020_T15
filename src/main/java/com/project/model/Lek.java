package com.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lekovi")
public class Lek {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lek_generator")
	@SequenceGenerator(name="lek_generator", sequenceName = "lek_seq")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="naziv", unique=false, nullable=false)
	private String naziv;
	
	/*
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sifarnik", joinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "dijagnoza_id", referencedColumnName = "id"))
	private Set<Dijagnoza> dijagnoze;
*/
	public Lek() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
/*
	public Set<Dijagnoza> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<Dijagnoza> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}
	*/
	
}
