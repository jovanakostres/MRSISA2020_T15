package com.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.model.Dijagnoza;
import com.project.model.Lek;

public class IzvestajDto {

	
	private String id;
	
	private String pregledId;
	
	private String informacije;
	
	private String dijagnozaId ;
	
	private LocalDate datumP;
	
	private LocalTime vremeP ;
	
	private LocalTime vremeZ ;
	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> lekoviIds;

	public IzvestajDto(String id, String pregledId, String informacije, String dijagnozaId, List<String> lekoviIds) {
		super();
		this.id = id;
		this.pregledId = pregledId;
		this.informacije = informacije;
		this.dijagnozaId = dijagnozaId;
		this.lekoviIds = lekoviIds;
	}

	public IzvestajDto(String pregledId, String informacije, String dijagnozaId, List<String> lekoviIds) {
		super();
		this.pregledId = pregledId;
		this.informacije = informacije;
		this.dijagnozaId = dijagnozaId;
		this.lekoviIds = lekoviIds;
	}
	
	public IzvestajDto(String id, String pregledId, String informacije, String dijagnozaId, LocalDate datumP,
			LocalTime vremeP, LocalTime vremeZ) {
		super();
		this.id = id;
		this.pregledId = pregledId;
		this.informacije = informacije;
		this.dijagnozaId = dijagnozaId;
		this.datumP = datumP;
		this.vremeP = vremeP;
		this.vremeZ = vremeZ;
	}

	public IzvestajDto() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPregledId() {
		return pregledId;
	}

	public void setPregledId(String pregledId) {
		this.pregledId = pregledId;
	}

	public String getInformacije() {
		return informacije;
	}

	public void setInformacije(String informacije) {
		this.informacije = informacije;
	}

	public String getDijagnozaId() {
		return dijagnozaId;
	}

	public void setDijagnozaId(String dijagnozaId) {
		this.dijagnozaId = dijagnozaId;
	}

	public List<String> getLekoviIds() {
		return lekoviIds;
	}

	public void setLekoviIds(List<String> lekoviIds) {
		this.lekoviIds = lekoviIds;
	}

	public LocalDate getDatumP() {
		return datumP;
	}

	public void setDatumP(LocalDate datumP) {
		this.datumP = datumP;
	}

	public LocalTime getVremeP() {
		return vremeP;
	}

	public void setVremeP(LocalTime vremeP) {
		this.vremeP = vremeP;
	}

	public LocalTime getVremeZ() {
		return vremeZ;
	}

	public void setVremeZ(LocalTime vremeZ) {
		this.vremeZ = vremeZ;
	}


	

	
}
