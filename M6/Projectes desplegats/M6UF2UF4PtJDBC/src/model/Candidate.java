package model;

import java.util.ArrayList;

public class Candidate {
	private Integer candidateId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	ArrayList<Prize> prizes;

	public Candidate() {

	}

	public Candidate(Integer candidateId, String firstName, String lastName, String phoneNumber, String email) {
		this.candidateId = candidateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.prizes = null;
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(ArrayList<Prize> prizes) {
		this.prizes = prizes;
	}

	@Override
	public String toString() {
		return "Candidate [Id=" + candidateId + ", First Name=" + firstName + ", Last Name=" + lastName
				+ ", Phone Number=" + phoneNumber + ", E-mail=" + email + ", Prizes=" + prizes + "]";
	}

}
