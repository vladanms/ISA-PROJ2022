package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonalFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personalFileSerialNo", unique = true, nullable = false)
	public int serialNo;
	
	public String fullName;
	
	public String JMBG;

	public String address;
	
	public String phone;
	
	public String company;
	
	public String occupation;
	
	public int previousDonationsNo;
	
	
	public boolean alreadyDonated;
	
	public boolean donationRefused;
	
	public boolean dangerousOccupation;
	
	public boolean prescriptionMeds;
	
	public boolean allergies;
	
	public boolean chronicIllness;
	
	public boolean termsAndConditions;

	
	
	
	public PersonalFile(int serialNo, String fullName, String jMBG, String address, String phone, String company,
			String occupation, int previousDonationsNo, boolean alreadyDonated, boolean donationRefused,
			boolean dangerousOccupation, boolean prescriptionMeds, boolean allergies, boolean chronicIllness,
			boolean termsAndConditions) {
		super();
		this.serialNo = serialNo;
		this.fullName = fullName;
		JMBG = jMBG;
		this.address = address;
		this.phone = phone;
		this.company = company;
		this.occupation = occupation;
		this.previousDonationsNo = previousDonationsNo;
		this.alreadyDonated = alreadyDonated;
		this.donationRefused = donationRefused;
		this.dangerousOccupation = dangerousOccupation;
		this.prescriptionMeds = prescriptionMeds;
		this.allergies = allergies;
		this.chronicIllness = chronicIllness;
		this.termsAndConditions = termsAndConditions;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getPreviousDonationsNo() {
		return previousDonationsNo;
	}

	public void setPreviousDonationsNo(int previousDonationsNo) {
		this.previousDonationsNo = previousDonationsNo;
	}

	public boolean isAlreadyDonated() {
		return alreadyDonated;
	}

	public void setAlreadyDonated(boolean alreadyDonated) {
		this.alreadyDonated = alreadyDonated;
	}

	public boolean isDonationRefused() {
		return donationRefused;
	}

	public void setDonationRefused(boolean donationRefused) {
		this.donationRefused = donationRefused;
	}

	public boolean isDangerousOccupation() {
		return dangerousOccupation;
	}

	public void setDangerousOccupation(boolean dangerousOccupation) {
		this.dangerousOccupation = dangerousOccupation;
	}

	public boolean isPrescriptionMeds() {
		return prescriptionMeds;
	}

	public void setPrescriptionMeds(boolean prescriptionMeds) {
		this.prescriptionMeds = prescriptionMeds;
	}

	public boolean isAllergies() {
		return allergies;
	}

	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}

	public boolean isChronicIllness() {
		return chronicIllness;
	}

	public void setChronicIllness(boolean chronicIllness) {
		this.chronicIllness = chronicIllness;
	}

	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	
	
	
}
