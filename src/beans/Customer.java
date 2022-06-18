package beans;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {

	private Membership membership;
	private ArrayList<SportFacility> visitedFacilities;
	private int points;
	private CustomerLevel customerLevel;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String lastName, String email, String username, String password, Gender gender,
			Date dateOfBirth, UserType userType, Membership membership) {
		super(firstName, lastName, email, username, password, gender, dateOfBirth, userType);
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

	public CustomerLevel getMembershipLevel() {
		return customerLevel;
	}

	public void setMembershipLevel(CustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}
	
	
	
	
}
