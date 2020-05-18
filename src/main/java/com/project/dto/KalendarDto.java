package com.project.dto;

import java.time.LocalDateTime;

public class KalendarDto {

	private String text;
	
	private LocalDateTime start_date;
	
	private LocalDateTime end_date;
	
	public KalendarDto() {
		
	}

	public KalendarDto(String text, LocalDateTime start_date, LocalDateTime end_date) {
		super();
		this.text = text;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	
	
}
