package main.dto;

import java.time.LocalDate;

public class PenalDTOView {
	private String date;
	private String email;
	public PenalDTOView() {
		super();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
