package beans;

import java.util.Date;

public class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Gender gender;
	private Date dateOfBirth;
	private UserType userType;
	
	
	public enum Gender{
		MALE,
		FEMALE
	}
	
	public enum UserType{
		CUSTOMER,
		ADMINISTRATOR,
		MANAGER,
		COACH
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, String username, String password,
			Gender gender, Date dateOfBirth, UserType userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
	
	
	
}
