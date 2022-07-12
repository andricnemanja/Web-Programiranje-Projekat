package factories;

import java.util.Calendar;
import beans.Coupon;
import beans.Customer;
import beans.CustomerLevel;
import beans.Membership;
import beans.Membership.MembershipStatus;
import beans.Membership.MembershipType;

public class MembershipFactory {
	
	private double MONTH_12_PRICE = 2499;
	private double MONTH_FULL_PRICE = 2999;
	private double YEAR_PRICE = 24999;

	public Membership getMembership(String id, String membershipType, Customer customer) {
		Calendar today = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		double price = calculatePrice(membershipType, customer.getCustomerLevel());
		
		if(membershipType.equals("MONTH_12")) {
			endDate.add(Calendar.MONTH, 1);
			return new Membership(id, MembershipType.MONTHLY, today.getTime(), endDate.getTime(), price, customer.getUsername(), MembershipStatus.ACTIVE, 12, 12);
		}
		if(membershipType.equals("MONTH_FULL")) {
			endDate.add(Calendar.MONTH, 1);
			return new Membership(id, MembershipType.MONTHLY, today.getTime(), endDate.getTime(), price, customer.getUsername(), MembershipStatus.ACTIVE, 31, 31);
		}
		
		endDate.add(Calendar.YEAR, 1);
		return new Membership(id, MembershipType.ANNUAL, today.getTime(), endDate.getTime(), price, customer.getUsername(), MembershipStatus.ACTIVE, 366, 366);
	}
	
	public Membership getMembership(String id, String membershipType, Customer customer, Coupon coupon) {
		Membership membership = getMembership(id, membershipType, customer);
		membership.setPrice(calculatePrice(membershipType, customer.getCustomerLevel(), coupon));
		return membership;
	}
	
	
	
	public double calculatePrice(String membershipType, CustomerLevel customerLevel) {
		if(membershipType.equals("MONTH_12")) {
			return MONTH_12_PRICE - MONTH_12_PRICE * customerLevel.getDiscount() / 100;
		}
		if(membershipType.equals("MONTH_FULL")) {
			return MONTH_FULL_PRICE - MONTH_FULL_PRICE * customerLevel.getDiscount() / 100;
		}
		return YEAR_PRICE - YEAR_PRICE * customerLevel.getDiscount() / 100;
	}
	
	public double calculatePrice(String membershipType, CustomerLevel customerLevel, Coupon coupon) {
		if(membershipType.equals("MONTH_12")) {
			return calculatePrice(membershipType, customerLevel) -  MONTH_12_PRICE * coupon.getDiscountPercentage() / 100;
		}
		if(membershipType.equals("MONTH_FULL")) {
			return calculatePrice(membershipType, customerLevel) -  MONTH_FULL_PRICE * coupon.getDiscountPercentage() / 100;
		}
		return calculatePrice(membershipType, customerLevel) -  YEAR_PRICE * coupon.getDiscountPercentage() / 100;
	}
	
	
}
