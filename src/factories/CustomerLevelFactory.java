package factories;

import beans.CustomerLevel;
import beans.CustomerLevel.CustomerType;

public class CustomerLevelFactory {
	public static CustomerLevel getCustomerLevel(CustomerType customerType) {
		
		switch(customerType) {
		case BRONZE:
			return new CustomerLevel(CustomerType.BRONZE, 0, 3000);
		case SILVER:
			return new CustomerLevel(CustomerType.SILVER, 3, 5000);
		case GOLD:
			return new CustomerLevel(CustomerType.GOLD, 5, 100000);
		default:
			return new CustomerLevel(CustomerType.BRONZE, 0, 3000);
		}
	}
	
	
	public static CustomerLevel getNextLevel(CustomerLevel customerLevel) {
		if(customerLevel.getCustomerType() == CustomerType.BRONZE)
			return new CustomerLevel(CustomerType.SILVER, 3, 5000);
		else if(customerLevel.getCustomerType() == CustomerType.SILVER)
			return new CustomerLevel(CustomerType.GOLD, 5, 100000);
		return new CustomerLevel(CustomerType.BRONZE, 0, 3000);
	}
}
