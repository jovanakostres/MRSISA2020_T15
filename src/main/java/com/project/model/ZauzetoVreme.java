package com.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
    @Enumerated(EnumType.STRING)
    private TipZauzetosti tipZauzetosti;
	
    @ManyToOne(fetch = FetchType.LAZY)
	private Lekar lekar;
    
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

	public TipZauzetosti getTipZauzetosti() {
		return tipZauzetosti;
	}

	public void setTipZauzetosti(TipZauzetosti tipZauzetosti) {
		this.tipZauzetosti = tipZauzetosti;
	}

	
	
	
}
