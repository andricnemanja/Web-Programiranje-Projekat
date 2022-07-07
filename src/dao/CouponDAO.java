package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import beans.Coupon;
import beans.Customer;
import beans.SportFacility;
import beans.Coupon.MembershipStatus;
import beans.Coupon.MembershipType;


public class CouponDAO extends DAO {

	private HashMap<String, Coupon> coupons = new HashMap<String, Coupon>();

	
	public CouponDAO() {
		super();
	}
	
	public CouponDAO(String contextPath) {
		super();
		loadCoupons(contextPath);
	}
	
	public Collection<Coupon> getAll() {
		return coupons.values();
	}
	
	public Collection<String> getAllCouponCodes() {
		ArrayList<String> codes = new ArrayList<>();
		for(Coupon coupon : coupons.values()) {
			if(isCouponValid(coupon.getCode()))
				codes.add(coupon.getCode());
		}
		return codes;
	}
	
	
	private void loadCoupons(String contextPath) {
		
        JSONArray membershipList = super.load(contextPath, "coupons.json");    
        for(Object obj : membershipList) {
        	parseJSONObject((JSONObject)obj);
        }
	}
	
	@Override
	protected void parseJSONObject(JSONObject customerJSONObject) {
		
		
		String code = (String) customerJSONObject.get("code");
		Date endDate = DateParser.parseDate((String) customerJSONObject.get("endDate"));
		double discountPercentage = (double) customerJSONObject.get("discountPercentage");
		int numberOfCoupons = Integer.valueOf((String) customerJSONObject.get("numberOfCoupons"));

		if(numberOfCoupons == 0) return;
		
		Coupon coupon = new Coupon(code, endDate, discountPercentage, numberOfCoupons); 
		coupons.put(code, coupon);

	}
	
	public boolean isCouponValid(String code) {
		if(!coupons.containsKey(code))
			return false;
		
		if(coupons.get(code).getNumberOfCoupons() <= 0)
			return false;
		
		Date todayDate = new Date();
		if(todayDate.after(coupons.get(code).getEndDate()))
			return false;
		
		return true;
	}
	
	public void useCoupon(String code) {
		if(!isCouponValid(code))
			return;
		
		Coupon coupon = coupons.get(code);
		int remainingCoupons = coupon.getNumberOfCoupons();
		coupon.setNumberOfCoupons(--remainingCoupons);
	}
	
	public Coupon getCoupon(String code) {
		if(!isCouponValid(code))
			return null;
		
		return coupons.get(code);
	}

}
