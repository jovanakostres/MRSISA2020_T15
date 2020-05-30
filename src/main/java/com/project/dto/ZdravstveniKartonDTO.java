package com.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.project.model.Pregled;
import com.project.model.ZdravstveniKarton;

public class ZdravstveniKartonDTO {
	
	private Integer visina;
	
	private Integer tezina;
	
	private String krvnaGrupa;
	
	private List<PregledDto> pregledi;
	
	public ZdravstveniKartonDTO(ZdravstveniKarton zk){
		this.visina = zk.getVisina();
		this.tezina = zk.getTezina();
		this.krvnaGrupa = zk.getKrvnaGrupa();
		this.pregledi = new ArrayList<PregledDto>();
		for(Pregled p : zk.getPregledi()) {
			this.pregledi.add(new PregledDto(p.getId(), p.getLekar().getIme(), p.getLekar().getPrezime(), p.getSala().getNaziv(), p.getDatum(), p.getVremeOd(),
					null, p.getCena(), p.getIzvrsen(), p.getTipPregleda().getIme()));
		}
	}

	public Integer getVisina() {
		return visina;
	}

	public void setVisina(Integer visina) {
		this.visina = visina;
	}

	public Integer getTezina() {
		return tezina;
	}

	public void setTezina(Integer tezina) {
		this.tezina = tezina;
	}

	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public List<PregledDto> getPregledi() {
		return pregledi;
	}

	public void setPregledi(List<PregledDto> pregledi) {
		this.pregledi = pregledi;
	}

	
}
