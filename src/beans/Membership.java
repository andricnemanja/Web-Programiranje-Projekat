package beans;

import java.util.Date;

public class Membership {

	private String id;
	private MembershipType membershipType;
	private Date paymentDate;
	private Date endDate;
	private double price;
	private Customer customer;
	private String customerUsername;
	private MembershipStatus membershipStatus;
	private int numberOfRemainingVisits;
	
	public enum MembershipType{
		ANNUAL,
		MONTHLY
	}
	public enum MembershipStatus{
		ACTIVE,
		INACTIVE
	}
	
	public Membership() {
		// TODO Auto-generated constructor stub
	}

	public Membership(String id, MembershipType membershipType, Date paymentDate, Date endDate,
			double price, String customerUsername, MembershipStatus membershipStatus, int numberOfRemainingVisits) {
		super();
		this.id = id;
		this.membershipType = membershipType;
		this.paymentDate = paymentDate;
		this.endDate = endDate;
		this.price = price;
		this.customerUsername = customerUsername;
		this.membershipStatus = membershipStatus;
		this.numberOfRemainingVisits = numberOfRemainingVisits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MembershipStatus getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(MembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public int getNumberOfRemainingVisits() {
		return numberOfRemainingVisits;
	}

	public void setNumberOfRemainingVisits(int numberOfRemainingVisits) {
		this.numberOfRemainingVisits = numberOfRemainingVisits;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	
	
	
}
