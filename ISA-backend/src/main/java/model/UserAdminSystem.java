package model;

import javax.persistence.Entity;

@Entity
public class UserAdminSystem extends User{

	public UserAdminSystem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdminSystem(String name, String surname, String email, String jmbg, String password, String address,
			String city, String country, String phone, Gender gender, UserType type, String occupation,
			String company) {
		super(name, surname, email, jmbg, password, address, city, country, phone, gender, type, occupation, company);
		// TODO Auto-generated constructor stub
	}

}
