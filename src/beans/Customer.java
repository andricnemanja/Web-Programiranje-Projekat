package beans;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {
	
	private String id;
	private Membership membership;
	private ArrayList<SportFacility> visitedFacilities;
	private int points;
	private CustomerLevel customerLevel;
	
	public Customer() {
		super();
		visitedFacilities = new ArrayList<>();
	}

	public Customer(String firstName, String lastName, String email, String username, String password,
			Date dateOfBirth, UserType userType, Membership membership) {
		super(firstName, lastName, email, username, password, Gender.MALE, dateOfBirth, userType);
		visitedFacilities = new ArrayList<SportFacility>();
		points = 0;
	}
	
	public Customer(String firstName, String lastName, String email, String username, String password, Gender gender,
			Date dateOfBirth) {
		super(firstName, lastName, email, username, password, gender, dateOfBirth, UserType.CUSTOMER);
		visitedFacilities = new ArrayList<SportFacility>();
		points = 0;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public ArrayList<SportFacility> getVisitedFacilities() {
		return visitedFacilities;
	}

	public void setVisitedFacilities(ArrayList<SportFacility> visitedFacilities) {
		this.visitedFacilities = visitedFacilities;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerLevel getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(CustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}
	
	
	
	
}
