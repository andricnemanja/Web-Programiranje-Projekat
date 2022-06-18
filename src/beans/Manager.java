package beans;

import java.util.Date;

public class Manager extends User{
	private SportFacility sportFacility;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String firstName, String lastName, String email, String username, String password, Gender gender,
			Date dateOfBirth, UserType userType) {
		super(firstName, lastName, email, username, password, gender, dateOfBirth, userType);
		// TODO Auto-generated constructor stub
	}

	public SportFacility getSportFacility() {
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}
	
	

}
