package main.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Center {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "centerId", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "centerName", nullable = false)
	private String name;
	@Column(name = "centerAddress", nullable = false)
	private String address;
	@Column(name = "centerDescription", nullable = false)
	private String description;
	//@Column(name = "centerGrade", nullable = false)
	//private ArrayList<Float> grade;
	@Column(name = "centerAvgGrade", nullable = false)
	private Float avgGrade;
	
	//@OneToMany(targetEntity = Appointment.class, fetch = FetchType.EAGER)
	//private Set<Appointment> Appointments;
	
	//@OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private ArrayList<User> admins;
	
	public Center() {
		super();
	}

	public Center(Long id, String name, String address, String description, ArrayList<Appointment> appointments, Float avgGrade) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.avgGrade = avgGrade;
		//this.grade = grade;
		//Appointments = Appointments;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	}*/

	public Float getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(Float avgGrade) {
		this.avgGrade = avgGrade;
	}

	/*public ArrayList<Appointment> getAppointments() {
		return Appointments;
	}

	public void setAppointments(ArrayList<Appointment> Appointments) {
		this.Appointments = Appointments;
	}*/

	/*public ArrayList<User> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<User> admins) {
		this.admins = admins;
	}
	
	public void addGrade(Float g)
	{
		this.grade.add(g);
	}*/
	
	
	
	
}

