package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="zauzeto_vreme")
public class ZauzetoVreme {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zauzeto_generator")
	@SequenceGenerator(name="zauzeto_generator", sequenceName = "zauzeto_seq")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="datum_od", unique=false, nullable=false)
	private LocalDate datumOd;
	
	@Column(name="datum_do", unique=false, nullable=false)
	private LocalDate datumDo;
	
	@Column(name="vreme_od", unique=false, nullable=false)
	private LocalTime vremeOd;
	
	@Column(name="vreme_do", unique=false, nullable=false)
	private LocalTime vremeDo;
	
	public ZauzetoVreme() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}

	public LocalDate getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(LocalDate datumDo) {
		this.datumDo = datumDo;
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
	
	
	
}
