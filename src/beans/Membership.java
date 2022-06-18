package beans;

import java.util.Date;

public class Membership {

	private String id;
	private MembershipType membershipType;
	private Date paymentDate;
	private Date startDateTime;
	private Date endDateTime;
	private double price;
	private Customer customer;
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

	public Membership(String id, MembershipType membershipType, Date paymentDate, Date startDateTime, Date endDateTime,
			double price, Customer customer, MembershipStatus membershipStatus, int numberOfRemainingVisits) {
		super();
		this.id = id;
		this.membershipType = membershipType;
		this.paymentDate = paymentDate;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.price = price;
		this.customer = customer;
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

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
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
	
	
	
}
