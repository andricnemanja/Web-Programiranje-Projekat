package beans;

import java.util.Date;

public class Coupon {
	private String code;
	private Date endDate;
	private double discountPercentage;
	private int numberOfCoupons;
	
	public Coupon(String code, Date endDate, double discountPercentage, int numberOfCoupons) {
		super();
		this.code = code;
		this.endDate = endDate;
		this.discountPercentage = discountPercentage;
		this.numberOfCoupons = numberOfCoupons;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public int getNumberOfCoupons() {
		return numberOfCoupons;
	}
	public void setNumberOfCoupons(int numberOfCoupons) {
		this.numberOfCoupons = numberOfCoupons;
	}
	
	
}
