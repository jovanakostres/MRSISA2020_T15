package com.project.dto;

public class LekDto {

	private Long idLeka;
	
	private String nazivLeka;

	public LekDto(String nazivLeka) {
		super();
		this.nazivLeka = nazivLeka;
	}
	
	public LekDto() {

	}

	public Long getIdLeka() {
		return idLeka;
	}

	public void setIdLeka(Long idLeka) {
		this.idLeka = idLeka;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}
	
	
}
