package dto;

import java.util.List;

import model.Appointment;
import model.User;

public class CenterDTO {

	private String name;
	private String address;
	private String description;
	private List<Float> grade;
	private List<Appointment> freeAppointments;
	private List<User> admins;
	private Float avgGrade;
	
	public CenterDTO(Long id, String name, String address, String description, List<Float> grade,
			List<Appointment> freeAppointments, List<User> admins) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.grade = grade;
		this.freeAppointments = freeAppointments;
		this.admins = admins;
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
		}
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

	public List<Float> getGrade() {
		return grade;
	}

	public void setGrade(List<Float> grade) {
		this.grade = grade;
	}

	public Float getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(Float avgGrade) {
		this.avgGrade = avgGrade;
	}

	public List<Appointment> getFreeAppointments() {
		return freeAppointments;
	}

	public void setFreeAppointments(List<Appointment> freeAppointments) {
		this.freeAppointments = freeAppointments;
	}

	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}
	
	
}
