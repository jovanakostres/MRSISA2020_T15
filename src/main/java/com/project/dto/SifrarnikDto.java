package com.project.dto;

public class SifrarnikDto {
	private Long idL;
	
	private Long idD;
	
	private String imeL;
	
	private String imeD;

	
	public SifrarnikDto() {

	}
	
	public SifrarnikDto(String imeL, String imeD) {
		super();
		this.imeL = imeL;
		this.imeD = imeD;
	}

	public SifrarnikDto(Long idL, Long idD) {
		super();
		this.idL = idL;
		this.idD = idD;
	}


	public SifrarnikDto(Long idL, Long idD, String imeL, String imeD) {
		super();
		this.idL = idL;
		this.idD = idD;
		this.imeL = imeL;
		this.imeD = imeD;
	}



	

	public Long getIdL() {
		return idL;
	}



	public void setIdL(Long idL) {
		this.idL = idL;
	}



	public Long getIdD() {
		return idD;
	}



	public void setIdD(Long idD) {
		this.idD = idD;
	}



	public String getImeL() {
		return imeL;
	}

	public void setImeL(String imeL) {
		this.imeL = imeL;
	}

	public String getImeD() {
		return imeD;
	}

	public void setImeD(String imeD) {
		this.imeD = imeD;
	}
	
	
}
