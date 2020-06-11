package com.project.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="zahtevi_za_odmor")
public class ZahtevZaOdmor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zahtev_odmor_generator")
	@SequenceGenerator(name="zahtev_odmor_generator", sequenceName = "zahtev_odmor_seq")
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="informacije", unique=true, nullable=false)
	private String informacije;
	
	@Column(name="datum_od", unique=false, nullable=false)
	private LocalDate datumOd;
	
	@Column(name="datum_do", unique=false, nullable=false)
	private LocalDate datumDo;
	
	@Enumerated(EnumType.STRING)
    private TipZauzetosti tipZauzetosti;

	public ZahtevZaOdmor() {

	}

	public ZahtevZaOdmor(String informacije, LocalDate datumOd, LocalDate datumDo, TipZauzetosti tipZauzetosti) {
		super();
		this.informacije = informacije;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.tipZauzetosti = tipZauzetosti;
	}
	
	public ZahtevZaOdmor(String informacije, LocalDate datumOd, LocalDate datumDo, String tipZauzetosti) {
		super();
		this.informacije = informacije;
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.tipZauzetosti = TipZauzetosti.valueOf(tipZauzetosti);
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
