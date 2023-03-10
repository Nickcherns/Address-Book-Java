package main;

public class Entry {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	
	public Entry() {
		
	}
	
	public Entry(String firstName, String lastName, String phoneNumber, String emailAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
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


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		String entry = "  Name: " + firstName + " " + lastName
						+ "\n  Phone Number: " + phoneNumber
						+ "\n  Email Address: " + emailAddress;
		return entry;
	}
}
