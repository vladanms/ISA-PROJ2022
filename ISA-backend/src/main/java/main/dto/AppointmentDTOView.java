package main.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTOView {
	private String id;
	private String centerName;
	private String date;
	private String time;
	
	public AppointmentDTOView() {
		super();
	}
	
	public AppointmentDTOView(String id, String centerName, String date, String time) {
		super();
		this.id = id;
		this.centerName = centerName;
		this.date = date;
		this.time = time;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
