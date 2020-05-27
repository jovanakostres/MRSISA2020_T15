package com.project.dto;

public class DijagnozaDto {
	
	private Long idDijagnoze;
	
	private String nazivDijagnoze;

	public DijagnozaDto(String nazivDijagnoze) {
		super();
		this.nazivDijagnoze = nazivDijagnoze;
	}

	public DijagnozaDto() {

	}
	
	public Long getIdDijagnoze() {
		return idDijagnoze;
	}

	public void setIdDijagnoze(Long idDijagnoze) {
		this.idDijagnoze = idDijagnoze;
	}

	public String getNazivDijagnoze() {
		return nazivDijagnoze;
	}

	public void setNazivDijagnoze(String nazivDijagnoze) {
		this.nazivDijagnoze = nazivDijagnoze;
	}
	
	
}
