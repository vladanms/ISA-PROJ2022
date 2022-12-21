package main.dto;

import javax.persistence.Column;

public class CenterDTOView {
	private int id;
	private String name;
	private String address;
	private String description;
	private Float avgGrade;
	
	public CenterDTOView() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the avgGrade
	 */
	public Float getAvgGrade() {
		return avgGrade;
	}
	/**
	 * @param avgGrade the avgGrade to set
	 */
	public void setAvgGrade(Float avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	
}
