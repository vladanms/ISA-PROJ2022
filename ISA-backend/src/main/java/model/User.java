package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "userName", nullable = false)
	private String name;
	
	@Column(name = "userSurname", nullable = false)
	private String surname;
	
	@Column(name = "userEmail", unique = true, nullable = false)
	private String email;
	
	@Column(name = "userJmbg", unique = true, nullable = false)
	private String jmbg;
	
	@Column(name = "userPassword", nullable = false)
	private String password;
	
	@Column(name = "userAddress", nullable = false)
	private String address;
	
	@Column(name = "userCity", nullable = false)
	private String city;
	
	@Column(name = "userCountry", nullable = false)
	private String country;
	
	@Column(name = "userPhone", nullable = false)
	private String phone;
	
	@Column(name = "userGender", nullable = false)
	private Gender gender;
	
	@Column(name = "userType", nullable = false)
	private UserType type;
	
	@Column(name = "userOccupation", nullable = false)
	private String occupation;
	
	@Column(name = "userCompany", nullable = false)
	private String company;
	
	
	public User() {
		super();
	}
	
	public User(String name, String surname, String email, String jmbg, String password, String address,
			String city, String country, String phone, Gender gender, UserType type, String occupation,
			String company) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.jmbg = jmbg;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.gender = gender;
		this.type = type;
		this.occupation = occupation;
		this.company = company;
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


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public UserType getType() {
		return type;
	}


	public void setType(UserType type) {
		this.type = type;
	}


	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}
	
}
