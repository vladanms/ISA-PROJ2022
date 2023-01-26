package main.dto;

import java.util.ArrayList;

import main.model.Appointment;
import main.model.User;

public class CenterDTO {

	private String name;
	private String address;
	private String description;
	//private ArrayList<Float> grade;
	private ArrayList<Appointment> appointments;
	private ArrayList<String> admins;
	private Float avgGrade;
	
	public CenterDTO(Long id, String name, String address, String description, Float avgGrade, ArrayList<Appointment> appointments, ArrayList<String> admins) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.avgGrade = avgGrade;
		//this.grade = grade;
		this.appointments = appointments;
		this.admins = admins;
		/*this.admins = admins;
		if(this.grade.size() > 0)
		{
			for(int i = 0; i <= this.grade.size(); i++)
			{
				avgGrade += this.grade.get(i);
			}
			avgGrade = avgGrade/this.grade.size();
		}
		else
		{
			avgGrade = (float) 0;
		}*/
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public ArrayList<Float> getGrade() {
		return grade;
	}

	public void setGrade(ArrayList<Float> grade) {
		this.grade = grade;
	}*/

	public Float getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(Float avgGrade) {
		this.avgGrade = avgGrade;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setFreeAppointments(ArrayList<Appointment> Appointments) {
		this.appointments = appointments;
	}

	public ArrayList<String> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<String> admins) {
		this.admins = admins;
	}
	
	
}
