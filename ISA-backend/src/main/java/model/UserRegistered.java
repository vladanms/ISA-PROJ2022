package model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserRegistered extends User {

	
	@Column(name = "points", nullable = false)
	private int points;
	
	@Column(name = "userCategory", nullable = false)
	private UserCategory category;
	
	public UserRegistered() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRegistered(String name, String surname, String email, String jmbg, String password, String address,
			String city, String country, String phone, Gender gender, UserType type, String occupation,
			String company) {
		super(name, surname, email, jmbg, password, address, city, country, phone, gender, type, occupation, company);
		this.category = UserCategory.Bronze;
		this.points = 0;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
		if(this.points > 1000)
		{
			this.category = UserCategory.Platinum;
		}
		else if(this.points > 600)
		{
			this.category = UserCategory.Gold;
		}
		else if(this.points > 300)
		{
			this.category = UserCategory.Silver;
		}
		else
			this.category = UserCategory.Bronze;
	}
	public UserCategory getCategory() {
		return category;
	}
	public void setCategory(UserCategory category) {
		this.category = category;
	}
	
	
}
