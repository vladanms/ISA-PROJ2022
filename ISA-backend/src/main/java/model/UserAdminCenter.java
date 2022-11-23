package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserAdminCenter extends User {
	
	@Column(name = "assignedCenters", nullable = false)
	private List<Center> assignedCenters;

	public UserAdminCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdminCenter(String name, String surname, String email, String jmbg, String password, String address,
			String city, String country, String phone, Gender gender, UserType type, String occupation,
			String company, List<Center> centers) {
		super(name, surname, email, jmbg, password, address, city, country, phone, gender, type, occupation, company);
		this.assignedCenters = centers;
	}

	public List<Center> getAssignedCenters() {
		return assignedCenters;
	}

	public void setAssignedCenters(List<Center> assignedCenters) {
		this.assignedCenters = assignedCenters;
	}
	
	

}
