package beans;

public class CustomerLevel {
	
	private CustomerType customerType;
	private double discount;
	private int pointsForNextLevel; 
	
	public enum CustomerType{
		BRONZE,
		SILVER,
		GOLD
	}

	public CustomerLevel(CustomerType customerType, double discount, int pointsForNextLevel) {
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

	public int getPointsForNextLevel() {
		return pointsForNextLevel;
	}

	public void setPointsForNextLevel(int pointsForNextLevel) {
		this.pointsForNextLevel = pointsForNextLevel;
	}

	
	
}
