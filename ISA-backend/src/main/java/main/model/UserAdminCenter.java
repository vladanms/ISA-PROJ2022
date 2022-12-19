package main.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAdminCenter extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private Long id;

	@Column(name = "assignedCenters", nullable = false)
	private Center centers;

	public UserAdminCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdminCenter(String name, String surname, String email, String jmbg, String password, String address,
			String city, String country, String phone, Gender gender, UserType type, String occupation,
			String company, Center center) {
		super(name, surname, email, jmbg, password, address, city, country, phone, gender, type, occupation, company);
		this.centers = center;
	}

	public Center getCenters() {
		return centers;
	}

	public void setCenters(Center centers) {
		this.centers = centers;
	}

	

}
