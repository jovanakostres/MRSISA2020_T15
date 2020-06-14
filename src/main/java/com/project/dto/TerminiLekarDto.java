package com.project.dto;

import java.time.LocalDate;
import java.util.List;

public class TerminiLekarDto {
	
	private List<Long> lekariIds;
	private LocalDate datum;
	
	public TerminiLekarDto() {}
	
	public TerminiLekarDto(List<Long> lekariIds, LocalDate datum) {
		super();
		this.lekariIds = lekariIds;
		this.datum = datum;
	}
	public List<Long> getLekariIds() {
		return lekariIds;
	}
	public void setLekariIds(List<Long> lekariIds) {
		this.lekariIds = lekariIds;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	
}
