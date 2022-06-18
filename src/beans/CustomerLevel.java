package beans;

import java.util.HashMap;

public class CustomerLevel {
	
	private CustomerType customerType;
	private double discount;
	private HashMap<CustomerType, Integer> pointsForNextLevel; 
	
	public enum CustomerType{
		BRONZE,
		SILVER,
		GOLD
	}

	public CustomerLevel(CustomerType customerType, double discount,
			HashMap<CustomerType, Integer> pointsForNextLevel) {
		super();
		this.customerType = customerType;
		this.discount = discount;
		this.pointsForNextLevel = pointsForNextLevel;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public HashMap<CustomerType, Integer> getPointsForNextLevel() {
		return pointsForNextLevel;
	}

	public void setPointsForNextLevel(HashMap<CustomerType, Integer> pointsForNextLevel) {
		this.pointsForNextLevel = pointsForNextLevel;
	}
	
	
	
	
}
